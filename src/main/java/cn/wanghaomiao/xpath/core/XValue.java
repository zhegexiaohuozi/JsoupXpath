package cn.wanghaomiao.xpath.core;

import cn.wanghaomiao.xpath.exception.XpathParserException;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2017/12/5.
 */
public class XValue implements Comparable<XValue> {
    public XValue(Object val){
        this.value = val;
    }

    public static XValue create(Object val){
        return new XValue(val);
    }
    private Object value;

    private boolean isAttr = false;
    private boolean isExprStr = false;

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

    public boolean isList() {
        return value instanceof List;
    }

    public Boolean asBoolean() {
        return (Boolean)value;
    }

    public Double asDouble() {
        if (value instanceof String){
            return new BigDecimal((String) value).doubleValue();
        }else if (value instanceof Number) {
            return ((Number)value).doubleValue();
        }else {
            throw new XpathParserException("cast to number fail. vale = "+value);
        }
    }

    public Long asLong() {
        if (value instanceof String){
            //对于带小数点的，四舍五入
            return new BigDecimal((String) value).setScale(0,BigDecimal.ROUND_HALF_UP).longValue();
        }else if (value instanceof Number) {
            return ((Number)value).longValue();
        }else {
            throw new XpathParserException("cast to number fail. vale = "+value);
        }
    }

    public Elements asElements() {
        return (Elements) value;
    }

    public String asString() {
        if (value instanceof List){
            return Joiner.on(',').join((List)value);
        }else {
            return ((String)value).trim();
        }
    }

    public List<String> asList(){
        return (List<String>) value;
    }

    public XValue attr(){
        this.isAttr = true;
        return this;
    }

    public boolean isAttr() {
        return isAttr;
    }

    public XValue exprStr(){
        this.isExprStr = true;
        String str = StringUtils.removeStart(String.valueOf(this.value),"'");
        str = StringUtils.removeStart(str,"\"");
        str = StringUtils.removeEnd(str,"'");
        this.value = StringUtils.removeEnd(str,"\"");
        return this;
    }

    public boolean isExprStr() {
        return isExprStr;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .add("isAttr", isAttr)
                .add("isExprStr", isExprStr)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XValue value1 = (XValue) o;
        return Objects.equal(value, value1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }


    @Override
    public int compareTo(XValue o) {
        if (this.equals(o)){
            return 0;
        }
        if (o == null||o.value == null){
            return 1;
        }
        if (this.value == null){
            return -1;
        }
        ComparisonChain chain = ComparisonChain.start();
        if (isString()){
            return chain.compare(asString(),o.asString()).result();
        }else if (isNumber()){
            return chain.compare(asDouble(),o.asDouble()).result();
        }else {
            throw new XpathParserException("Unsupported comparable XValue = "+toString());
        }
    }
}
