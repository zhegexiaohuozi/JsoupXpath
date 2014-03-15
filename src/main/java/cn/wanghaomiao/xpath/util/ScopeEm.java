package cn.wanghaomiao.xpath.util;

/**
 * 作用域
 * @author 汪浩淼 [ et.tw@163.com ]
 * @since 14-3-7 下午4:26
 */
public enum ScopeEm {
    INCHILREN("/"),   //只在子代中查找
    RECURSIVE("//"),  //向下递归查找
    CUR("./"),        //当前节点下
    CURREC(".//");    //当前节点向下递归
    private String val;
    private ScopeEm(String type){
        this.val = type;
    }
    public String val() {
        return this.val;
    }
}
