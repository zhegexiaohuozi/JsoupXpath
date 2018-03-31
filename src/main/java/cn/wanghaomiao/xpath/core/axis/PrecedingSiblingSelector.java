package cn.wanghaomiao.xpath.core.axis;

import cn.wanghaomiao.xpath.core.AxisSelector;
import cn.wanghaomiao.xpath.core.XValue;
import cn.wanghaomiao.xpath.util.CommonUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * the preceding-sibling axis contains all the preceding siblings of the context node; if the context node is
 * an attribute node or namespace node, the preceding-sibling axis is empty
 *
 * @author: github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/3/27.
 */
public class PrecedingSiblingSelector implements AxisSelector {
    /**
     * assign name
     *
     * @return name
     */
    @Override
    public String name() {
        return "preceding-sibling";
    }

    /**
     * @param context
     * @return res
     */
    @Override
    public XValue apply(Elements context) {
        Set<Element> total = new HashSet<>();
        for (Element el : context){
            Elements ps = CommonUtil.precedingSibling(el);
            if (ps == null){
                continue;
            }
            total.addAll(ps);
        }
        Elements newContext = new Elements();
        newContext.addAll(total);
        return XValue.create(newContext);
    }
}
