package cn.wanghaomiao.xpath.core;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.model.Node;
import cn.wanghaomiao.xpath.model.Predicate;
import cn.wanghaomiao.xpath.util.ScopeEm;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
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
public class NodeTreeEvaluator {
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
        for (Node n:xpathNodes){
            LinkedList<Element> contextTmp = new LinkedList<Element>();
            if (n.getScopeEm()== ScopeEm.RECURSIVE||n.getScopeEm()==ScopeEm.CURREC){
                Elements searchRes = context.select(n.getTagName());
                for (Element e:searchRes){
                    Element filterR = filter(e,n);
                    if (filterR!=null){
                        contextTmp.add(filterR);
                    }
                }
                context = new Elements(contextTmp);
            }else {
                if (n.getTagName().startsWith("@")){
                     for (Element e:context){
                         String value = e.attr(n.getTagName().substring(1));
                         if (StringUtils.isNotBlank(value)){
                             res.add(value);
                         }
                     }
                }else if (n.getTagName().endsWith("()")){
                    res = callFunc(n.getTagName().substring(0,n.getTagName().length()-2),context);
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
    public Element filter(Element e,Node node){
        //todo step1:根据tagName筛选
        //todo step2:根据predicate筛选
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

    public List<Object> callFunc(String funcname,Elements context) throws NoSuchFunctionException {
        try {
            Method function = Functions.class.getMethod(funcname,Elements.class);
            return (List<Object>) function.invoke(SingletonProducer.getInstance().getFunctions(),context);
        } catch (NoSuchMethodException e) {
            throw new NoSuchFunctionException("This function is not supported");
        } catch (Exception e1) {
            throw new NoSuchFunctionException(e1.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Elements els=Jsoup.connect("http://www.baidu.com").get().select("div");
        System.out.println(new NodeTreeEvaluator().getXpathNodeTree("//div[@class='aa']/child::li[contains(./text(),'abc')]/li[5]"));
    }
}
