package cn.wanghaomiao.xpath.core.node;

import cn.wanghaomiao.xpath.core.NodeTest;
import cn.wanghaomiao.xpath.core.Scope;
import cn.wanghaomiao.xpath.core.XValue;
import cn.wanghaomiao.xpath.exception.XpathParserException;
import cn.wanghaomiao.xpath.util.Scanner;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提取自由文本中的数字，返回double
 * @author: github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/3/26.
 */
public class Num implements NodeTest {
    private static Pattern numExt = Pattern.compile("\\d*\\.?\\d+");
    /**
     * 支持的函数名
     */
    @Override
    public String name() {
        return "num";
    }

    /**
     * 函数具体逻辑
     *
     * @param scope 上下文
     * @return 计算好的节点
     */
    @Override
    public XValue call(Scope scope) {
        NodeTest textFun = Scanner.findNodeTestByName("allText");
        XValue textVal = textFun.call(scope);
        String whole = StringUtils.join(textVal.asList(),"");
        Matcher matcher = numExt.matcher(whole);
        if (matcher.find()){
            String numStr = matcher.group();
            BigDecimal num = new BigDecimal(numStr);
            return XValue.create(num.doubleValue());
        }else {
            return XValue.create(null);
        }
    }
}
