package cn.wanghaomiao.xpath.model;
/*
   Copyright 2014 Wang Haomiao<et.tw@163.com>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
import cn.wanghaomiao.xpath.util.ScopeEm;

/**
 * xpath语法链的一个基本节点
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com]
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
