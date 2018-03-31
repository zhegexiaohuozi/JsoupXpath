package cn.wanghaomiao.xpath.core.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import cn.wanghaomiao.xpath.core.AxisSelector;
import cn.wanghaomiao.xpath.core.XValue;

import java.util.HashSet;
import java.util.Set;

/**
 * the parent axis contains the parent of the context node, if there is one
 * https://www.w3.org/TR/1999/REC-xpath-19991116/#NT-AxisSpecifier
 * @author: github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/2/28.
 */
public class ParentSelector implements AxisSelector {
    @Override
    public String name() {
        return "parent";
    }

    @Override
    public XValue apply(Elements context) {
        Set<Element> total = new HashSet<>();
        Elements parents = new Elements();
        for (Element el:context){
            total.add(el.parent());
        }
        parents.addAll(total);
        return XValue.create(parents);
    }
}
