package org.seimicrawler.xpath.core.node;

import org.jsoup.nodes.TextNode;
import org.seimicrawler.xpath.core.Constants;
import org.seimicrawler.xpath.core.NodeTest;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.util.CommonUtil;
import org.seimicrawler.xpath.util.Scanner;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/2/28.
 */
public class Text implements NodeTest {
    /**
     * 支持的函数名
     */
    @Override
    public String name() {
        return "text";
    }

    /**
     * 函数具体逻辑
     *
     * @param scope 上下文
     * @return 计算好的节点
     */
    @Override
    public XValue call(Scope scope) {
        Elements context = scope.context();
        Elements res = new Elements();
        if (context!=null&&context.size()>0){
            if (scope.isRecursion()){
                for (Element e:context){
                    Elements all = e.getAllElements();
                    for (Element c:all){
                        List<TextNode> textNodes =  c.textNodes();
                        for (int i=0;i<textNodes.size();i++){
                            TextNode textNode = textNodes.get(i);
                            Element data = new Element(Constants.DEF_TEXT_TAG_NAME);
                            data.text(textNode.getWholeText());
                            CommonUtil.setSameTagIndexInSiblings(data,i+1);
                            res.add(data);
                        }
                    }
                }
            }else {
                for (Element e:context){
                    if ("script".equals(e.nodeName())){
                        Element data = new Element(Constants.DEF_TEXT_TAG_NAME);
                        data.text(e.data());
                        CommonUtil.setSameTagIndexInSiblings(data,1);
                        res.add(data);
                    }else {
                        List<TextNode> textNodes =  e.textNodes();
                        for (int i=0;i<textNodes.size();i++){
                            TextNode textNode = textNodes.get(i);
                            Element data = new Element(Constants.DEF_TEXT_TAG_NAME);
                            data.text(textNode.getWholeText());
                            CommonUtil.setSameTagIndexInSiblings(data,i+1);
                            res.add(data);
                        }
                    }
                }
            }
        }
        return XValue.create(res);
    }
}
