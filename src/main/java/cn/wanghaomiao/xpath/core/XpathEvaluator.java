package cn.wanghaomiao.xpath.core;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.model.Node;
import cn.wanghaomiao.xpath.model.Predicate;
import cn.wanghaomiao.xpath.util.ScopeEm;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 汪浩淼 [ et.tw@163.com ]
 * @since 14-3-12 下午3:42
 */
public class XpathEvaluator {
    /**
     * 获取xpath解析语法树
     * @param xpath
     * @return
     */
    public List<Node> getXpathNodeTree(String xpath){
        NodeTreeBuilderStateMachine st = new NodeTreeBuilderStateMachine();
        while (st.state != NodeTreeBuilderStateMachine.BuilderState.END){
            st.state.parser(st, xpath.toCharArray());
        }
        return st.context.xpathTr;
    }

    /**
     * 根据xpath求出结果
     * @param xpath
     * @param root
     * @return
     */
    public List<Object> evaluate(String xpath,Elements root) throws NoSuchAxisException, NoSuchFunctionException {
        List<Object> res = new LinkedList<Object>();
        Elements context = root;
        List<Node> xpathNodes=getXpathNodeTree(xpath);
        for (int i=0;i<xpathNodes.size();i++){
            Node n = xpathNodes.get(i);
            LinkedList<Element> contextTmp = new LinkedList<Element>();
            if (n.getScopeEm()== ScopeEm.RECURSIVE||n.getScopeEm()==ScopeEm.CURREC){
                if (n.getTagName().startsWith("@")){
                    for (Element e:context){
                        //处理上下文自身节点
                        String key = n.getTagName().substring(1);
                        if (key.equals("*")){
                            res.add(e.attributes().toString());
                        }else {
                            String value = e.attr(key);
                            if (StringUtils.isNotBlank(value)){
                                res.add(value);
                            }
                        }
                        //处理上下文子代节点
                        for (Element dep:e.getAllElements()){
                            if (key.equals("*")){
                                res.add(dep.attributes().toString());
                            }else {
                                String value = dep.attr(key);
                                if (StringUtils.isNotBlank(value)){
                                    res.add(value);
                                }
                            }
                        }
                    }
                }else if (n.getTagName().endsWith("()")){
                    //递归执行方法默认只支持text()
                    res.add(context.text());
                }else {
                    Elements searchRes = context.select(n.getTagName());
                    for (Element e:searchRes){
                        Element filterR = filter(e,n);
                        if (filterR!=null){
                            contextTmp.add(filterR);
                        }
                    }
                    context = new Elements(contextTmp);
                }

            }else {
                if (n.getTagName().startsWith("@")){
                     for (Element e:context){
                         String key = n.getTagName().substring(1);
                         if (key.equals("*")){
                             res.add(e.attributes().toString());
                         }else {
                             String value = e.attr(key);
                             if (StringUtils.isNotBlank(value)){
                                 res.add(value);
                             }
                         }
                     }
                }else if (n.getTagName().endsWith("()")){
                    res = (List<Object>) callFunc(n.getTagName().substring(0,n.getTagName().length()-2),context);
                }else {
                    for (Element e:context){
                        Elements filterScope = e.children();
                        if (StringUtils.isNotBlank(n.getAxis())){
                            filterScope = getAxisScopeEls(n.getAxis(),e);
                        }
                        for (Element chi:filterScope){
                            Element fchi=filter(chi,n);
                            if (fchi!=null){
                                contextTmp.add(fchi);
                            }
                        }
                    }
                    context=new Elements(contextTmp);
                    if (i==xpathNodes.size()-1){
                        res.addAll(contextTmp);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 元素过滤器
     * @param e
     * @param node
     * @return
     */
    public Element filter(Element e,Node node) throws NoSuchFunctionException, NoSuchAxisException {
        if (node.getTagName().equals("*")||node.getTagName().equals(e.nodeName())){
            if (node.getPredicate()!=null){
                Predicate p = node.getPredicate();
                if (p.getOpEm()==null){
                    if (p.getValue().matches("\\d+")&&e.siblingIndex()==Integer.parseInt(p.getValue())){
                        return e;
                    }else if (p.getValue().endsWith("()")&&(Boolean)callFilterFunc(p.getValue().substring(0,p.getValue().length()-2),e)){
                        return e;
                    }
                    //todo p.value ~= contains(./@href,'renren.com')
                }else {
                    if (p.getLeft().endsWith("()")){
                        Object filterRes=p.getOpEm().excute(callFilterFunc(p.getLeft().substring(0,p.getLeft().length()-2),e).toString(),p.getRight());
                        if (filterRes instanceof Boolean && (Boolean) filterRes){
                            return e;
                        }else if(filterRes instanceof Integer && e.siblingIndex()==Integer.parseInt(filterRes.toString())){
                            return e;
                        }
                    }else if (p.getLeft().startsWith("@")){
                        String lValue = e.attr(p.getLeft().substring(1));
                        Object filterRes = p.getOpEm().excute(lValue,p.getRight());
                        if ((Boolean) filterRes){
                            return e;
                        }
                    }else {
                        // 操作符左边不是函数、属性默认就是xpath表达式了
                        List<Element> eltmp = new LinkedList<Element>();
                        eltmp.add(e);
                        List<Object> rstmp=evaluate(p.getLeft(),new Elements(eltmp));
                        if ((Boolean) p.getOpEm().excute(StringUtils.join(rstmp,""),p.getRight())){
                            return e;
                        }
                    }
                }
            }else {
                return e;
            }
        }
        return null;
    }

    public Elements getAxisScopeEls(String axis,Element e) throws NoSuchAxisException {
        try {
            Method axisSelector = AxisSelector.class.getMethod(axis, Element.class);
            return (Elements) axisSelector.invoke(SingletonProducer.getInstance().getAxisSelector(),e);
        }catch (NoSuchMethodException e1) {
            throw new NoSuchAxisException("this axis is not supported,plase use other instead of '"+axis+"'");
        } catch (Exception e2) {
            throw new NoSuchAxisException(e2.getMessage());
        }
    }

    public Object callFunc(String funcname,Elements context) throws NoSuchFunctionException {
        try {
            Method function = Functions.class.getMethod(funcname,Elements.class);
            return function.invoke(SingletonProducer.getInstance().getFunctions(),context);
        } catch (NoSuchMethodException e) {
            throw new NoSuchFunctionException("This function is not supported");
        } catch (Exception e1) {
            throw new NoSuchFunctionException(e1.getMessage());
        }
    }

    public Object callFilterFunc(String funcname,Element el) throws NoSuchFunctionException {
        try {
            Method function = Functions.class.getMethod(funcname,Element.class);
            return function.invoke(SingletonProducer.getInstance().getFunctions(),el);
        } catch (NoSuchMethodException e) {
            throw new NoSuchFunctionException("This function is not supported");
        } catch (Exception et) {
            throw new NoSuchFunctionException(et.getMessage());
        }
    }

    public static void main(String[] args) throws IOException, NoSuchFunctionException, NoSuchAxisException {
        Document d = Jsoup.connect("http://www.baidu.com").get();
        XpathEvaluator b =  new XpathEvaluator();
        String xp = "//div[0]/a/@href";
        List<Object> rs = b.evaluate(xp,d.children());
        for (Object o:rs){
            System.out.println(o.toString());
        }
    }

}
