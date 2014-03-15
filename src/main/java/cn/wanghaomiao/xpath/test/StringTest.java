package cn.wanghaomiao.xpath.test;

import java.util.StringTokenizer;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 13-12-16 下午5:10
 */
public class StringTest {
    public static void main(String[] args){
        String expression = "//div[@class='aa']/li[contains(./text(),'abc')]";
        StringTokenizer tokenizer = new StringTokenizer(expression, "/()[]\"'=<>\\.@", true);
        System.out.println(tokenizer);
        while (tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
    }
}
