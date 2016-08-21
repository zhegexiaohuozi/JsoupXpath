package cn.wanghaomiao.xpath.util;
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
import org.apache.commons.lang3.StringUtils;

/**
 * 操作符
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com]
 * @since 14-3-7
 */
public enum OpEm {
    PLUS("+"){
        @Override
        public Object excute(String left,String right) {
            int li = 0;
            if (StringUtils.isNotBlank(left)){
                li = Integer.parseInt(left);
            }
            int ri = 0;
            if (StringUtils.isNotBlank(right)){
                ri=Integer.parseInt(right);
            }
            return li + ri;
        }
    },
    MINUS("-"){
        @Override
        public Object excute(String left, String right) {
            int li = 0;
            if (StringUtils.isNotBlank(left)){
                li = Integer.parseInt(left);
            }
            int ri = 0;
            if (StringUtils.isNotBlank(right)){
                ri=Integer.parseInt(right);
            }
            return li - ri;
        }
    },
    EQ("="){
        @Override
        public Object excute(String left, String right) {
            return left.equals(right);
        }
    },
    NE("!="){
        @Override
        public Object excute(String left, String right) {
            return !left.equals(right);
        }
    },
    GT(">"){
        @Override
        public Object excute(String left, String right) {
            int li = 0;
            if (StringUtils.isNotBlank(left)){
                li = Integer.parseInt(left);
            }
            int ri = 0;
            if (StringUtils.isNotBlank(right)){
                ri=Integer.parseInt(right);
            }
            return li > ri;
        }
    },
    LT("<"){
        @Override
        public Object excute(String left, String right) {
            int li = 0;
            if (StringUtils.isNotBlank(left)){
                li = Integer.parseInt(left);
            }
            int ri = 0;
            if (StringUtils.isNotBlank(right)){
                ri=Integer.parseInt(right);
            }
            return li < ri;
        }
    },
    GE(">="){
        @Override
        public Object excute(String left, String right) {
            int li = 0;
            if (StringUtils.isNotBlank(left)){
                li = Integer.parseInt(left);
            }
            int ri = 0;
            if (StringUtils.isNotBlank(right)){
                ri=Integer.parseInt(right);
            }
            return li >= ri;
        }
    },
    LE("<="){
        @Override
        public Object excute(String left, String right) {
            int li = 0;
            if (StringUtils.isNotBlank(left)){
                li = Integer.parseInt(left);
            }
            int ri = 0;
            if (StringUtils.isNotBlank(right)){
                ri=Integer.parseInt(right);
            }
            return li <= ri;
        }
    },
    STARTWITH("^="){
        @Override
        public Object excute(String left, String right) {
            return left.startsWith(right);
        }
    },
    ENDWITH("$="){
        @Override
        public Object excute(String left, String right) {
            return left.endsWith(right);
        }
    },
    CONTAIN("*="){
        @Override
        public Object excute(String left, String right) {
            return left.contains(right);
        }
    },
    REGEX("~="){
        @Override
        public Object excute(String left, String right) {
            return left.matches(right);
        }
    },
    NOTMATCH("!~"){
        @Override
        public Object excute(String left, String right) {
            return !left.matches(right);
        }
    };
    private String val;
    private OpEm(String type){
        this.val = type;
    }
    public String val() {
        return this.val;
    }

    public Object excute(String left,String right){
        return null;
    }
}
