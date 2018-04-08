package org.seimicrawler.xpath.core.axis;

import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.XValue;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * the preceding-sibling-one axis JsoupXpath自定义扩展，用于选取节点的前一个兄弟节点，如果存在的话。
 *
 * @author github.com/hermitmmll
 * @since 2018/3/27.
 */
public class PrecedingSiblingOneSelector implements AxisSelector {
    /**
     * assign name
     *
     * @return name
     */
    @Override
    public String name() {
        return "preceding-sibling-one";
    }

    /**
     * @param context
     * @return res
     */
    @Override
    public XValue apply(Elements context) {
        Set<Element> total = new HashSet<>();
        for (Element el : context){
            if (el.previousElementSibling()!=null){
                total.add(el);
            }
        }
        Elements newContext = new Elements();
        newContext.addAll(total);
        return XValue.create(newContext);
    }
}
