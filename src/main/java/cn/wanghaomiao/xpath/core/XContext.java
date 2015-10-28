package cn.wanghaomiao.xpath.core;

import cn.wanghaomiao.xpath.model.Node;

import java.util.LinkedList;

/**
 * @author 汪浩淼 [ et.tw@163.com ]
 * @since 14-3-10
 */
public class XContext {
    public LinkedList<Node> xpathTr;
    public XContext(){
        if (xpathTr==null){
            xpathTr = new LinkedList<Node>();
        }
    }
}
