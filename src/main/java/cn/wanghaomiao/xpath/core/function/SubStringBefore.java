package cn.wanghaomiao.xpath.core.function;

import cn.wanghaomiao.xpath.core.Scope;
import org.apache.commons.lang3.StringUtils;
import cn.wanghaomiao.xpath.core.Function;
import cn.wanghaomiao.xpath.core.XValue;

import java.util.List;

/**
 * Function: string substring-before(string, string)
 *
 * The substring-before function returns the substring of the first argument string that precedes the
 *  first occurrence of the second argument string in the first argument string, or the empty string
 *  if the first argument string does not contain the second argument string.
 *  For example, substring-before("1999/04/01","/") returns 1999.
 *
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/3/26.
 */
public class SubStringBefore implements Function{
    @Override
    public String name() {
        return "substring-before";
    }

    @Override
    public XValue call(Scope scope, List<XValue> params) {
        String target = params.get(0).asString();
        String sep = params.get(1).asString();
        return XValue.create(StringUtils.substringBefore(target,sep));
    }
}
