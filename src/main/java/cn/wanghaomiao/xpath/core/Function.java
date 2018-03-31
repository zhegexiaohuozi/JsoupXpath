package cn.wanghaomiao.xpath.core;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/2/28.
 */
public interface Function {
    String name();
    XValue call(Element context, List<XValue> params);
}
