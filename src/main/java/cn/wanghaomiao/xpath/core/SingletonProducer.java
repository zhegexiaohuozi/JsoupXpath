package cn.wanghaomiao.xpath.core;

/**
 * @author: 汪浩淼 [ et.tw@163.com ]
 * Date: 14-3-16 下午6:29
 */
public class SingletonProducer {
    private static SingletonProducer producer = new SingletonProducer();
    private AxisSelector axisSelector = new AxisSelector();
    private Functions functions =new Functions();

    public static SingletonProducer getInstance() {
        return producer;
    }

    public AxisSelector getAxisSelector() {
        return axisSelector;
    }

    public Functions getFunctions() {
        return functions;
    }

    private SingletonProducer() {
    }
}
