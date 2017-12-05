package org.seimicrawler.xpath.core;

import org.jsoup.select.Elements;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2017/12/5.
 */
public class XValue {

    public XValue(Object value) {
        this.value = value;
        if(!(isBoolean() || isElements() || isNumber() || isString())) {
            throw new RuntimeException("invalid data type: " + value + " (" + value.getClass() + ")");
        }
    }

    Object value;

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    public boolean isNumber() {
        return value instanceof Number;
    }

    public boolean isElements() {
        return value instanceof Elements;
    }

    public boolean isString() {
        return value instanceof String;
    }

    public Boolean asBoolean() {
        return (Boolean)value;
    }

    public Double asDouble() {
        return ((Number)value).doubleValue();
    }

    public Long asLong() {
        return ((Number)value).longValue();
    }

    public Elements asElements() {
        return (Elements) value;
    }

    public String asString() {
        return (String)value;
    }
}
