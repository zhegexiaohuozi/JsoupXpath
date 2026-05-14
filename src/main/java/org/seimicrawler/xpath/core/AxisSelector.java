package org.seimicrawler.xpath.core;

import org.jsoup.select.Elements;

/**
 * https://www.w3.org/TR/1999/REC-xpath-19991116/#NT-AxisSpecifier
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/2/28.
 */
public interface AxisSelector {
    /**
     * assign name
     * @return name
     */
    String name();

    /**
     * @param context 当前上下文元素集合
     * @return res
     */
    XValue apply(Elements context);
}
