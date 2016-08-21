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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com]
 * @since 14-3-11
 */
public class EmMap {
    public Map<String,ScopeEm> scopeEmMap = new HashMap<String, ScopeEm>();
    public Map<String,OpEm> opEmMap = new HashMap<String, OpEm>();
    public Set<Character> commOpChar = new HashSet<Character>();
    private static EmMap ourInstance = new EmMap();

    public static EmMap getInstance() {
        return ourInstance;
    }

    private EmMap() {
        scopeEmMap.put("/",ScopeEm.INCHILREN);
        scopeEmMap.put("//",ScopeEm.RECURSIVE);
        scopeEmMap.put("./",ScopeEm.CUR);
        scopeEmMap.put(".//",ScopeEm.CURREC);

        opEmMap.put("+",OpEm.PLUS);
        opEmMap.put("-",OpEm.MINUS);
        opEmMap.put("=",OpEm.EQ);
        opEmMap.put("!=",OpEm.NE);
        opEmMap.put(">",OpEm.GT);
        opEmMap.put("<",OpEm.LT);
        opEmMap.put(">=",OpEm.GE);
        opEmMap.put("<=",OpEm.LE);
        opEmMap.put("^=",OpEm.STARTWITH);
        opEmMap.put("$=",OpEm.ENDWITH);
        opEmMap.put("*=",OpEm.CONTAIN);
        opEmMap.put("~=",OpEm.REGEX);
        opEmMap.put("!~",OpEm.NOTMATCH);

        commOpChar.add('+');
        commOpChar.add('-');
        commOpChar.add('=');
        commOpChar.add('*');
        commOpChar.add('^');
        commOpChar.add('$');
        commOpChar.add('~');
        commOpChar.add('>');
        commOpChar.add('<');
        commOpChar.add('!');
    }
}
