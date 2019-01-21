package org.seimicrawler.xpath.core.function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.exception.XpathParserException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Function: string format-date(string, string)
 * The format-date function returns the substring of the first argument string that follows
 * the first occurrence of the second argument string in the first argument string, or the empty string if
 * the first argument string does not contain the second argument string.
 * For example, substring-after("1999/04/01","/") returns 04/01, and substring-after("1999/04/01","19") returns 99/04/01.
 * @author github.com/zzldn@163.com
 * @since 2018/3/26.
 */
public class FormatDate implements Function {
    @Override
    public String name() {
        return "format-date";
    }

    @Override
    public XValue call(Scope scope, List<XValue> params) {
        String value = params.get(0).asString();
        String patten = params.get(1).asString();
        try {
            if(params.size()>2&&null!=params.get(2)){
                final Locale locale = Locale.forLanguageTag(params.get(2).asString());
                final SimpleDateFormat format=new SimpleDateFormat(patten,locale);
                return XValue.create(format.parse(value));
            }
            return XValue.create(FastDateFormat.getInstance(patten).parse(value));
        } catch (ParseException e) {
            throw new XpathParserException("date format exception!",e);
        }

    }
}
