package org.seimicrawler.xpath.core.axis;

import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.XValue;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * the ancestor axis contains the ancestors of the context node; the ancestors of the context node consist of
 * the parent of context node and the parent's parent and so on; thus, the ancestor axis will always include
 * the root node, unless the context node is the root node
 *
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/3/26.
 */
public class AncestorSelector implements AxisSelector {
    @Override
    public String name() {
        return "ancestor";
    }

    @Override
    public XValue apply(Elements context) {
        Set<Element> total = new HashSet<>();
        Elements ancestor = new Elements();
        for (Element el:context){
            total.addAll(el.parents());
        }
        ancestor.addAll(total);
        return XValue.create(ancestor);
    }
}
