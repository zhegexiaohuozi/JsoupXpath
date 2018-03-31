package cn.wanghaomiao.xpath.core.axis;

import cn.wanghaomiao.xpath.core.AxisSelector;
import cn.wanghaomiao.xpath.core.XValue;
import cn.wanghaomiao.xpath.util.CommonUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * the following-sibling axis contains all the following siblings of the context node; if the context node is an
 * attribute node or namespace node, the following-sibling axis is empty
 *
 * @author: github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/3/27.
 */
public class FollowingSiblingSelector implements AxisSelector {
    /**
     * assign name
     *
     * @return name
     */
    @Override
    public String name() {
        return "following-sibling";
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
            Elements fs = CommonUtil.followingSibling(el);
            if (fs == null){
                continue;
            }
            total.addAll(fs);
        }
        Elements newContext = new Elements();
        newContext.addAll(total);
        return XValue.create(newContext);
    }
}
