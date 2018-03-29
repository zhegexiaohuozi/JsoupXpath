package cn.wanghaomiao.xpath.core.function;

import cn.wanghaomiao.xpath.core.Function;
import cn.wanghaomiao.xpath.core.XValue;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * first in xpath is 1
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/3/29.
 */
public class First implements Function {
    @Override
    public String name() {
        return "first";
    }

    @Override
    public XValue call(Element context, List<XValue> params) {
        return XValue.create(1);
    }
}
