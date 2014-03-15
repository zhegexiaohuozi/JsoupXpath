package cn.wanghaomiao.xpath.model;

import cn.wanghaomiao.xpath.util.ScopeEm;

/**
 * xpath语法链的一个基本节点
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-3-7 下午3:36
 */
public class Node {
    private ScopeEm scopeEm;
    private String axis;
    private String tagName;
    private Predicate predicate;

    public ScopeEm getScopeEm() {
        return scopeEm;
    }

    public void setScopeEm(ScopeEm scopeEm) {
        this.scopeEm = scopeEm;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

}
