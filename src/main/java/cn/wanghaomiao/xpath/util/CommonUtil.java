package cn.wanghaomiao.xpath.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author: 汪浩淼 [ et.tw@163.com ]
 * Date: 14-3-15 下午10:31
 */
public class CommonUtil {
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

    /**
     * 获取同名元素在同胞中的index
     * @param e
     * @return
     */
    public static int getElIndexInSameTags(Element e){
        Elements chs = e.parent().children();
        int index = 1;
        for(int i=0;i<chs.size();i++){
            Element cur = chs.get(i);
            if (e.tagName().equals(cur.tagName())){
                if (e.equals(cur)){
                    break;
                }else {
                    index+=1;
                }
            }
        }
        return index;
    }

    /**
     * 获取同胞中同名元素的数量
     * @param e
     * @return
     */
    public static int sameTagElNums(Element e){
        Elements els = e.parent().getElementsByTag(e.tagName());
        return els.size();
    }
}
