package cn.wanghaomiao.xpath.core;

import cn.wanghaomiao.xpath.antlr.XpathBaseVisitor;
import cn.wanghaomiao.xpath.antlr.XpathParser;
import cn.wanghaomiao.xpath.exception.XpathMergeValueException;
import cn.wanghaomiao.xpath.exception.XpathParserException;
import cn.wanghaomiao.xpath.util.Scanner;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

import static cn.wanghaomiao.xpath.antlr.XpathParser.*;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2017/8/30.
 */
public class XpathProcessor extends XpathBaseVisitor<XValue> {
    private Stack<Scope> scopeStack = new Stack<>();
    public XpathProcessor(Elements root){
        scopeStack.push(Scope.create(root));
    }

    @Override
    public XValue visitMain(XpathParser.MainContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public XValue visitLocationPath(XpathParser.LocationPathContext ctx) {
        if (ctx.relativeLocationPath()!=null&&!ctx.relativeLocationPath().isEmpty()){
            return visit(ctx.relativeLocationPath());
        }
        return visit(ctx.absoluteLocationPathNoroot());
    }

    @Override
    public XValue visitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
        // '//'
        if (Objects.equals(ctx.op.getText(),"//")){
            currentScope().recursion();
        }
        return visit(ctx.relativeLocationPath());
    }

    @Override
    public XValue visitRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {
        XValue finalVal = null;
        for (int i = 0;i<ctx.getChildCount();i++){
            ParseTree step = ctx.getChild(i);
            if (step instanceof XpathParser.StepContext){
                finalVal = visit(step);
                if (finalVal.isElements()){
                    updateCurrentContext(finalVal.asElements());
                }
            }else {
                if ("//".equals(step.getText())){
                    currentScope().recursion();
                }
            }
        }
        return finalVal;
    }

    @Override
    public XValue visitStep(XpathParser.StepContext ctx) {
        if (ctx.abbreviatedStep()!=null&&!ctx.abbreviatedStep().isEmpty()){
            return visit(ctx.abbreviatedStep());
        }
        boolean filterByAttr = false;
        boolean isAxisOk = false;
        if (ctx.axisSpecifier()!=null&&!ctx.axisSpecifier().isEmpty()){
            XValue axis = visit(ctx.axisSpecifier());
            if (axis!=null){
                isAxisOk = true;
                if (axis.isElements()){
                    updateCurrentContext(axis.asElements());
                }else if (axis.isAttr()){
                    filterByAttr = true;
                }
            }
        }
        if (ctx.nodeTest()!=null&&!ctx.nodeTest().isEmpty()){
            XValue nodeTest = visit(ctx.nodeTest());
            if (filterByAttr){
                Elements context = currentScope().context();
                String attrName = nodeTest.asString();
                if (context.size() == 1){
                    Element el = currentScope().singleEl();
                    return XValue.create(el.attr(attrName));
                }else {
                    List<String> attrs = new LinkedList<>();
                    for (Element el:context){
                        attrs.add(el.attr(attrName));
                    }
                    return XValue.create(attrs);
                }
            }else {
                if (nodeTest.isExprStr()){
                    String tagName = nodeTest.asString();
                    Elements current = currentScope().context();
                    if (currentScope().isRecursion()){
                        updateCurrentContext(current.select(tagName));
                    }else {
                        Elements newContext = new Elements();
                        for (Element el:currentScope().context()){
                            if (isAxisOk){
                                if (el.nodeName().equals(tagName)||"*".equals(tagName)){
                                    newContext.add(el);
                                }
                            }else {
                                for (Element e:el.children()){
                                    if (e.nodeName().equals(tagName)||"*".equals(tagName)){
                                        newContext.add(e);
                                    }
                                }
                            }

                        }
                        updateCurrentContext(newContext);
                    }
                }else {
                    // nodeType 计算结果，直接返给上层
                    return nodeTest;
                }
            }
        }
        if (ctx.predicate()!=null&&ctx.predicate().size()>0){
            for(XpathParser.PredicateContext predicate: ctx.predicate()){
                XValue predicateVal = visit(predicate);
                updateCurrentContext(predicateVal.asElements());
            }
        }
        return XValue.create(currentScope().context());
    }

    @Override
    public XValue visitAbbreviatedStep(XpathParser.AbbreviatedStepContext ctx) {
        if ("..".equals(ctx.getText())){
            Set<Element> total = new HashSet<>();
            Elements newContext = new Elements();
            for (Element e:currentScope().context()){
                total.add(e.parent());
            }
            newContext.addAll(total);
            return XValue.create(newContext);
        }else {
            return XValue.create(currentScope().context());
        }
    }

    @Override
    public XValue visitAxisSpecifier(XpathParser.AxisSpecifierContext ctx) {
        TerminalNode axisNode = ctx.AxisName();
        if (axisNode != null){
            String axis = ctx.AxisName().getText();
            AxisSelector axisSelector = Scanner.findSelectorByName(axis);
            return axisSelector.apply(currentScope().context());
        }else {
            String token = ctx.getText();
            if ("@".equals(token)){
                return XValue.create(null).attr();
            }
        }
        return null;
    }

    @Override
    public XValue visitNodeTest(XpathParser.NodeTestContext ctx) {
        if (ctx.nameTest()!=null){
            return visit(ctx.nameTest());
        }if (ctx.NodeType()!=null){
            NodeTest nodeTest = Scanner.findNodeTestByName(ctx.NodeType().getText());
            return nodeTest.call(currentScope());
        }
        // todo:   |  'processing-instruction' '(' Literal ')'
        return null;
    }

    @Override
    public XValue visitPredicate(XpathParser.PredicateContext ctx) {
        Elements newContext = new Elements();
        for (int i=0;i<currentScope().context().size();i++){
            Element e = currentScope().context().get(i);
            scopeStack.push(Scope.create(e).setParent(currentScope()));
            XValue exprVal = visit(ctx.expr());
            scopeStack.pop();
            if (exprVal.isNumber()){
                long index = exprVal.asLong();
                if (index < 0){
                    index = currentScope().context().size() + index + 1;
                }
                if (index == i+1){
                    newContext.add(e);
                }
            }else if (exprVal.isBoolean()){
                if (exprVal.asBoolean()){
                    newContext.add(e);
                }
            }else {
                throw new XpathParserException("unknown expr val:"+exprVal);
            }
        }
        return XValue.create(newContext);
    }

    @Override
    public XValue visitNameTest(XpathParser.NameTestContext ctx) {
        if ("*".equals(ctx.getText())){
            return XValue.create("*").exprStr();
        }else if (ctx.qName() != null&&!ctx.qName().isEmpty()){
            return visit(ctx.qName());
        }else if (ctx.nCName()!=null&&!ctx.nCName().isEmpty()){
            return visit(ctx.nCName());
        }
        return null;
    }

    @Override
    public XValue visitQName(XpathParser.QNameContext ctx) {
        List<XpathParser.NCNameContext> ncNameContexts =ctx.nCName();
        if (ncNameContexts!=null){
            if (ncNameContexts.size()>1){
                List<String> ncNames = new LinkedList<>();
                for (XpathParser.NCNameContext ncNameContext:ncNameContexts){
                    XValue value = visit(ncNameContext);
                    if (value!=null){
                        ncNames.add(value.asString());
                    }
                }
                // TODO: 2018/3/14 考虑体现出这个结构来
                return XValue.create(StringUtils.join(ncNames,":"));
            }else {
                return visit(ncNameContexts.get(0));
            }
        }
        return null;
    }

    @Override
    public XValue visitNCName(XpathParser.NCNameContext ctx) {
        if (ctx.AxisName()!=null){
            return XValue.create(ctx.AxisName().getText()).exprStr();
        }else {
            return XValue.create(ctx.NCName().getText()).exprStr();
        }
    }

    @Override
    public XValue visitExpr(XpathParser.ExprContext ctx) {
        return visit(ctx.orExpr());
    }

    @Override
    public XValue visitOrExpr(XpathParser.OrExprContext ctx) {
        List<XpathParser.AndExprContext> andExprContexts = ctx.andExpr();
        if (andExprContexts.size()>1){
            Boolean res = visit(andExprContexts.get(0)).asBoolean();
            for (int i=1;i<andExprContexts.size();i++){
                res = (res | visit(andExprContexts.get(i)).asBoolean());
            }
            return XValue.create(res);
        }else {
            return visit(andExprContexts.get(0));
        }
    }

    @Override
    public XValue visitAndExpr(XpathParser.AndExprContext ctx) {
        List<XpathParser.EqualityExprContext> equalityExprContexts = ctx.equalityExpr();
        if (equalityExprContexts.size()>1){
            Boolean res = visit(equalityExprContexts.get(0)).asBoolean();
            for (int i=1;i<equalityExprContexts.size();i++){
                res = (res & visit(equalityExprContexts.get(i)).asBoolean());
            }
            return XValue.create(res);
        }else {
            return visit(equalityExprContexts.get(0));
        }
    }

    @Override
    public XValue visitEqualityExpr(XpathParser.EqualityExprContext ctx) {
        List<XpathParser.RelationalExprContext> relationalExprContexts = ctx.relationalExpr();
        if (relationalExprContexts.size()==1){
            return visit(relationalExprContexts.get(0));
        }else if (relationalExprContexts.size()==2){
            XValue left = visit(relationalExprContexts.get(0));
            XValue right = visit(relationalExprContexts.get(1));
            if ("=".equals(ctx.op.getText())){
                return XValue.create(Objects.equals(left ,right));
            }else {
                return XValue.create(!Objects.equals(left,right));
            }
        }else {
            throw new XpathParserException("error equalityExpr near:"+ctx.getText());
        }
    }

    @Override
    public XValue visitRelationalExpr(XpathParser.RelationalExprContext ctx) {
        List<XpathParser.AdditiveExprContext> additiveExprContexts = ctx.additiveExpr();
        if (additiveExprContexts.size() == 1){
            return visit(additiveExprContexts.get(0));
        }else if (additiveExprContexts.size()==2){
            XValue left = visit(additiveExprContexts.get(0));
            XValue right = visit(additiveExprContexts.get(1));
            switch (ctx.op.getType()){
                case MORE_:
                    return XValue.create(left.compareTo(right) > 0);
                case GE:
                    return XValue.create(left.compareTo(right) >= 0);
                case LESS:
                    return XValue.create(left.compareTo(right) < 0);
                case LE:
                    return XValue.create(left.compareTo(right) <= 0);
                case START_WITH:
                    return XValue.create(left.asString().startsWith(right.asString()));
                case END_WITH:
                    return XValue.create(left.asString().endsWith(right.asString()));
                case CONTAIN_WITH:
                    return XValue.create(left.asString().contains(right.asString()));
                case REGEXP_WITH:
                    return XValue.create(left.asString().matches(right.asString()));
                case REGEXP_NOT_WITH:
                    return XValue.create(!left.asString().matches(right.asString()));
                default:
                    throw new XpathParserException("unknown operator"+ctx.op.getText());
            }
        }else {
            throw new XpathParserException("error equalityExpr near:"+ctx.getText());
        }
    }

    @Override
    public XValue visitAdditiveExpr(XpathParser.AdditiveExprContext ctx) {
        List<XpathParser.MultiplicativeExprContext> multiplicativeExprContexts = ctx.multiplicativeExpr();
        if (multiplicativeExprContexts.size() == 1){
            return visit(multiplicativeExprContexts.get(0));
        }else {
            Double res = visit(multiplicativeExprContexts.get(0)).asDouble();
            String op = null;
            for (int i=1;i<ctx.getChildCount();i++){
                ParseTree chiCtx = ctx.getChild(i);
                if (chiCtx instanceof XpathParser.MultiplicativeExprContext){
                    XValue next = visit(chiCtx);
                    if ("+".equals(op)){
                        res+=next.asDouble();
                    }else if ("-".equals(op)){
                        res-=next.asDouble();
                    }else {
                        throw new XpathParserException("syntax error, "+ctx.getText());
                    }
                }else {
                    op = chiCtx.getText();
                }
            }
            return XValue.create(res);
        }
    }

    @Override
    public XValue visitMultiplicativeExpr(XpathParser.MultiplicativeExprContext ctx) {
        if (ctx.multiplicativeExpr() == null||ctx.multiplicativeExpr().isEmpty()){
            return visit(ctx.unaryExprNoRoot());
        }else {
            XValue left = visit(ctx.unaryExprNoRoot());
            XValue right = visit(ctx.multiplicativeExpr());
            switch (ctx.op.getType()){
                case MUL:
                    return XValue.create(left.asDouble() * right.asDouble());
                case DIVISION:
                    return XValue.create(left.asDouble() / right.asDouble());
                case MODULO:
                    return XValue.create(left.asDouble() % right.asDouble());
                default:
                    throw new XpathParserException("syntax error, "+ctx.getText());
            }
        }
    }

    @Override
    public XValue visitUnaryExprNoRoot(XpathParser.UnaryExprNoRootContext ctx) {
        XValue value = visit(ctx.unionExprNoRoot());
        if (ctx.sign == null){
            return value;
        }
        return XValue.create(-value.asDouble());
    }

    @Override
    public XValue visitUnionExprNoRoot(XpathParser.UnionExprNoRootContext ctx) {
        if (ctx.pathExprNoRoot()== null&&!ctx.pathExprNoRoot().isEmpty()){
            return visit(ctx.unionExprNoRoot());
        }
        XValue pathExprNoRoot = visit(ctx.pathExprNoRoot());
        if (ctx.op == null){
            return pathExprNoRoot;
        }
        XValue unionExprNoRoot = visit(ctx.unionExprNoRoot());
        if (pathExprNoRoot.isElements()){
            if (unionExprNoRoot.isElements()){
                pathExprNoRoot.asElements().addAll(unionExprNoRoot.asElements());
            }else {
                Element element = new Element("V");
                element.appendText(unionExprNoRoot.asString());
                pathExprNoRoot.asElements().add(element);
            }
            return pathExprNoRoot;
        }else if (pathExprNoRoot.isString()){
            if (unionExprNoRoot.isElements()){
                Element element = new Element("V");
                element.appendText(pathExprNoRoot.asString());
                unionExprNoRoot.asElements().add(element);
                return unionExprNoRoot;
            }else {
                return XValue.create(pathExprNoRoot.asString()+unionExprNoRoot.asString());
            }
        }else if (pathExprNoRoot.isBoolean()){
            if (unionExprNoRoot.isBoolean()){
                return XValue.create(pathExprNoRoot.asBoolean()|unionExprNoRoot.asBoolean());
            }else if (unionExprNoRoot.isElements()){
                Element element = new Element("V");
                element.appendText(pathExprNoRoot.asString());
                unionExprNoRoot.asElements().add(element);
                return unionExprNoRoot;
            }else if (unionExprNoRoot.isString()){
                return XValue.create(pathExprNoRoot.asBoolean()+unionExprNoRoot.asString());
            }else {
                throw new XpathMergeValueException("can not merge val1="+pathExprNoRoot.asBoolean()+",val2="+unionExprNoRoot.asString());
            }
        }else if (pathExprNoRoot.isNumber()){
            if (unionExprNoRoot.isString()){
                return XValue.create(pathExprNoRoot.asDouble()+unionExprNoRoot.asString());
            }else if (unionExprNoRoot.isElements()){
                Element element = new Element("V");
                element.appendText(pathExprNoRoot.asString());
                unionExprNoRoot.asElements().add(element);
                return unionExprNoRoot;
            }else {
                throw new XpathMergeValueException("can not merge val1="+pathExprNoRoot.asDouble()+",val2="+unionExprNoRoot.asString());
            }
        }else {
            throw new XpathMergeValueException("can not merge val1="+pathExprNoRoot.asString()+",val2="+unionExprNoRoot.asString());
        }
    }

    @Override
    public XValue visitPathExprNoRoot(XpathParser.PathExprNoRootContext ctx) {
        if (ctx.locationPath()!=null&&!ctx.locationPath().isEmpty()){
            return visit(ctx.locationPath());
        }
        if (ctx.op == null){
            return visit(ctx.filterExpr());
        }
        if ("//".equals(ctx.op.getText())){
            currentScope().recursion();
        }
        return visit(ctx.relativeLocationPath());
    }

    @Override
    public XValue visitFilterExpr(XpathParser.FilterExprContext ctx) {
        //暂时先不考虑支持   :  primaryExpr predicate*
        return visit(ctx.primaryExpr());
    }

    @Override
    public XValue visitPrimaryExpr(XpathParser.PrimaryExprContext ctx) {
        if (ctx.expr()!=null&&!ctx.expr().isEmpty()){
            return visit(ctx.expr());
        }else if (ctx.functionCall()!=null&&!ctx.functionCall().isEmpty()){
            return visit(ctx.functionCall());
        }else if (ctx.Literal()!=null){
            return XValue.create(ctx.Literal().getText()).exprStr();
        }else if (ctx.Number()!=null){
            return XValue.create(NumberUtils.createDouble(ctx.Number().getText()));
        }
        throw new XpathParserException("not support variableReference:"+ctx.getText());
    }

    @Override
    public XValue visitFunctionCall(XpathParser.FunctionCallContext ctx) {
        List<XValue> params = new LinkedList<>();
        XValue funcName = visit(ctx.functionName());
        for (XpathParser.ExprContext exprContext:ctx.expr()){
            scopeStack.push(Scope.create(currentScope()));
            params.add(visit(exprContext));
            scopeStack.pop();
        }
        Function function = Scanner.findFunctionByName(funcName.asString());
        return function.call(currentScope(),params);
    }

    @Override
    public XValue visitFunctionName(XpathParser.FunctionNameContext ctx) {
        return visit(ctx.qName());
    }

    private Scope currentScope(){
        return scopeStack.peek();
    }

    private void updateCurrentContext(Elements newContext){
        scopeStack.peek().setContext(newContext);
    }

}
