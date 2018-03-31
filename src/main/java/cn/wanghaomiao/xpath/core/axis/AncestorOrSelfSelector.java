package cn.wanghaomiao.xpath.core.axis;

import cn.wanghaomiao.xpath.core.AxisSelector;
import cn.wanghaomiao.xpath.core.XValue;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * the ancestor-or-self axis contains the context node and the ancestors of the context node;
 * thus, the ancestor axis will always include the root node
 *
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/3/26.
 */
public class AncestorOrSelfSelector implements AxisSelector {
    @Override
    public String name() {
        return "ancestor-or-self";
    }

    @Override
    public XValue apply(Elements context) {
        Set<Element> total = new HashSet<>();
        Elements ancestor = new Elements();
        for (Element el:context){
            total.addAll(el.parents());
            //include self
            total.add(el);
        }
        ancestor.addAll(total);
        return XValue.create(ancestor);
    }
}
