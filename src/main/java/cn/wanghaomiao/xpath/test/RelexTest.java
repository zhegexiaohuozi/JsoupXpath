package cn.wanghaomiao.xpath.test;

import cn.wanghaomiao.xpath.core.Functions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: 汪浩淼 [ et.tw@163.com ]
 * Date: 14-3-15 下午8:21
 */
public class RelexTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Method method= Functions.class.getMethod("exTest",String.class,int.class);
//        method.invoke(Functions.class.newInstance(),"tt",5);
        method.invoke(new Functions(),"tt",5);
        System.out.println("123".substring(1));
    }
}
