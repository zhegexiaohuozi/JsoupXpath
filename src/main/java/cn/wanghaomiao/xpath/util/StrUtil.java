package cn.wanghaomiao.xpath.util;

/**
 * @author: 汪浩淼 [ et.tw@163.com ]
 * Date: 14-3-15 下午10:31
 */
public class StrUtil {
    public static String getJMethodNameFromStr(String str){
        if (str.contains("-")){
            String[] pies = str.split("-");
            StringBuilder sb = new StringBuilder(pies[0]);
            for (int i=1;i<pies.length;i++){
                sb.append(pies[i].substring(0,1).toUpperCase()).append(pies[i].substring(1));
            }
            return sb.toString();
        }
        return str;
    }
}
