package cn.wanghaomiao.xpath.core.axis;

import cn.wanghaomiao.xpath.core.AxisSelector;
import cn.wanghaomiao.xpath.core.XValue;
import cn.wanghaomiao.xpath.util.CommonUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * the following axis contains all nodes in the same document as the context node that are after the context node in
 * document order, excluding any descendants and excluding attribute nodes and namespace nodes
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/3/26.
 */
public class FollowingSelector implements AxisSelector {
    @Override
    public String name() {
        return "following";
    }

    @Override
    public XValue apply(Elements context) {
        Elements following = new Elements();
        Set<Element> total = new HashSet<>();
        for (Element el:context){
            Elements p = el.parents();
            for (Element pe: p){
                Elements fs = CommonUtil.followingSibling(pe);
                if (fs==null){
                    continue;
                }
                total.addAll(fs);
            }
            Elements fs = CommonUtil.followingSibling(el);
            if (fs==null){
                continue;
            }
            total.addAll(fs);
        }
        following.addAll(total);
        return XValue.create(following);
    }
}
