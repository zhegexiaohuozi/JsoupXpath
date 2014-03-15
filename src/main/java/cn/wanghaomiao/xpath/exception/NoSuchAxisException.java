package cn.wanghaomiao.xpath.exception;

/**
 * 使用不存在的轴语法则抛出此异常
 * @author: 汪浩淼 [ et.tw@163.com ]
 * Date: 14-3-15 下午10:14
 */
public class NoSuchAxisException extends Exception {
    public NoSuchAxisException(String msg){
        super(msg);
    }
}
