package cn.wanghaomiao.xpath.core.function;

import cn.wanghaomiao.xpath.core.Scope;
import cn.wanghaomiao.xpath.core.Function;
import cn.wanghaomiao.xpath.core.XValue;
import cn.wanghaomiao.xpath.util.CommonUtil;

import java.util.List;

/**
 * The position function returns a number equal to the context position from the expression evaluation context.
 * e.g.
 *  /child::doc/child::chapter[position()=5]/child::section[position()=2] selects the second section of the fifth chapter of the doc document element
 *
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/2/28.
 */
public class Position implements Function {
    @Override
    public String name() {
        return "position";
    }

    @Override
    public XValue call(Scope scope, List<XValue> params) {
        return XValue.create(CommonUtil.getElIndexInSameTags(scope.singleEl(),scope.getParent()));
    }
}
