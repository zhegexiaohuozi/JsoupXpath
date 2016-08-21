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
import cn.wanghaomiao.xpath.util.OpEm;

/**
 * xpath语法节点的谓语部分，即要满足的限定条件
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com]
 */
public class Predicate {

    private OpEm opEm;
    private String left;
    private String right;
    private String value;

    public OpEm getOpEm() {
        return opEm;
    }

    public void setOpEm(OpEm opEm) {
        this.opEm = opEm;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
