package cn.wanghaomiao.xpath.core;

import cn.wanghaomiao.xpath.model.Node;
import cn.wanghaomiao.xpath.model.Predicate;
import cn.wanghaomiao.xpath.util.ScopeEm;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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
    public List<Object> evaluate(String xpath,Elements root){
        LinkedList<Object> res = new LinkedList<Object>();
        Elements context = root;
        List<Node> xpathNodes=getXpathNodeTree(xpath);
        for (Node n:xpathNodes){
            LinkedList<Element> elstmp = new LinkedList<Element>();
            if (n.getScopeEm()== ScopeEm.RECURSIVE||n.getScopeEm()==ScopeEm.CURREC){
                Elements searchRes = context.select(n.getTagName());
                for (Element e:searchRes){
                    Element filterR = filter(e,n.getPredicate());
                    if (filterR!=null){
                        elstmp.add(filterR);
                    }
                }
            }else {
                if (n.getTagName().startsWith("@")){
                     for (Element e:context){
                         String value = e.attr(n.getTagName().substring(1));
                         if (StringUtils.isNotBlank(value)){
                             res.add(value);
                         }
                     }
                }else if (n.getTagName().endsWith("()")){
                    //todo call func

                }else {
                    LinkedList<Element> contextTmp = new LinkedList<Element>();
                    for (Element e:context){
                        for (Element chi:e.children()){
                            Element fchi=filter(chi,n.getPredicate());
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

    public Element filter(Element e,Predicate predicate){
        return null;
    }

    public static void main(String[] args) throws IOException {
        Elements els=Jsoup.connect("http://www.baidu.com").get().select("div");
        System.out.println(new NodeTreeEvaluator().getXpathNodeTree("//div[@class='aa']/child::li[contains(./text(),'abc')]/li[5]"));
    }
}
