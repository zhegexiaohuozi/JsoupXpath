package org.seimicrawler.xpath.core.node;

import org.seimicrawler.xpath.core.NodeTest;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;
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
        List<String> res = new LinkedList<>();
        if (context!=null&&context.size()>0){
            if (scope.isRecursion()){
                NodeTest allTextFun = Scanner.findNodeTestByName("allText");
                return allTextFun.call(scope);
            }else {
                for (Element e:context){
                    if ("script".equals(e.nodeName())){
                        res.add(e.data());
                    }else {
                        res.add(e.ownText());
                    }
                }
            }
        }
        return XValue.create(res);
    }
}
