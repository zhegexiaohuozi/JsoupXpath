package cn.wanghaomiao.xpath.test;

import cn.wanghaomiao.xpath.util.OpEm;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 13-12-16 下午5:10
 */
public class StringTest {
    public static void main(String[] args) throws IOException {
        String expression = "//div[@class='aa']/li[contains(./text(),'abc')]";
        StringTokenizer tokenizer = new StringTokenizer(expression, "/()[]\"'=<>\\.@", true);
        System.out.println(tokenizer);
        while (tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
        String s = "abcc(/div[1]/li[congtain(text(),'aa')],'tt')";
        System.out.println(s.substring(0, s.length() - 2));
        System.out.println(OpEm.GE.excute("5","6") instanceof Boolean);
        StringBuilder sb = new StringBuilder();
        sb.insert(0,"a");
        sb.insert(0,"b");
        System.out.println(sb.toString());
        System.out.println("5".matches("\\d+"));
        System.out.println(Jsoup.connect("http://www.baidu.com").get().select("a").first().firstElementSibling());

    }
}
