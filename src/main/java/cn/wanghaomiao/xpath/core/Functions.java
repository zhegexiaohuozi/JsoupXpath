package cn.wanghaomiao.xpath.core;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: 汪浩淼 [ et.tw@163.com ]
 * Date: 14-3-15 下午8:14
 */
public class Functions {
    public String exTest(String a,int b){
        System.out.println(a+b);
        return a+b;
    }

    /**
     * 递归获取节点内全部的纯文本
     * @param context
     * @return
     */
    public List<Object> text(Elements context){
        List<Object> res = new LinkedList<Object>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(e.text());
            }
        }
        return res;
    }

    /**
     * 只获取节点自身的子文本
     * @param context
     * @return
     */
    public List<Object> ownText(Elements context){
        List<Object> res = new LinkedList<Object>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(e.ownText());
            }
        }
        return res;
    }

    /**
     * 获取全部节点的内部的html
     * @param context
     * @return
     */
    public List<Object> html(Elements context){
        List<Object> res = new LinkedList<Object>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(e.html());
            }
        }
        return res;
    }

    /**
     * 获取全部节点的 包含节点本身在内的全部html
     * @param context
     * @return
     */
    public List<Object> outerHtml(Elements context){
        List<Object> res = new LinkedList<Object>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(e.outerHtml());
            }
        }
        return res;
    }
}
