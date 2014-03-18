package cn.wanghaomiao.xpath.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 汪浩淼 [ et.tw@163.com ]
 * @since 14-3-11 下午6:00
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
