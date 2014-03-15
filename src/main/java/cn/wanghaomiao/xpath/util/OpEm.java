package cn.wanghaomiao.xpath.util;

/**
 * 操作符
 * @author 汪浩淼 [ et.tw@163.com ]
 * @since 14-3-7 下午3:43
 */
public enum OpEm {
    PLUS("+"),MINUS("-"),EQ("="),NE("!="),GT(">"),LT("<"),GE(">="),LE("<="),
    STARTWITH("^="),ENDWITH("$="),CONTAIN("*="),REGEX("~=");
    private String val;
    private OpEm(String type){
        this.val = type;
    }
    public String val() {
        return this.val;
    }
}
