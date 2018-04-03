package cn.wanghaomiao.xpath.core.axis;

import cn.wanghaomiao.xpath.core.AxisSelector;
import cn.wanghaomiao.xpath.core.XValue;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * the following-sibling-one JsoupXpath自定义扩展,比较常用
 *
 * @author https://github.com/hermitmmll
 * @since 2018/3/27.
 */
public class FollowingSiblingOneSelector implements AxisSelector {
    /**
     * assign name
     *
     * @return name
     */
    @Override
    public String name() {
        return "following-sibling-one";
    }

    /**
     *
     * @param context
     * @return res
     */
    @Override
    public XValue apply(Elements context) {
        Set<Element> total = new HashSet<>();
        for (Element el : context){
            if (el.nextElementSibling()!=null){
                total.add(el.nextElementSibling());
            }
        }
        Elements newContext = new Elements();
        newContext.addAll(total);
        return XValue.create(newContext);
    }
}
