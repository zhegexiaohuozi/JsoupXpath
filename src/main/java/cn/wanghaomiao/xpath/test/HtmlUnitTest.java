package cn.wanghaomiao.xpath.test;

import java.io.IOException;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-2-7 下午2:21
 */
public class HtmlUnitTest {
    public static void main(String[] args) throws IOException {
        String url = "https://ssl.hrbeu.edu.cn/web/1/http/1/elib.cnki.net/grid2008/jump.aspx?url=http://epub.cnki.net/kns/loginid.aspx?uid={UID}&p=download.aspx?filename=G52T5U0bll0T2FFTBBVe4VUeOdUcxdGc5hnM21UVo5mVWZlU0s2SXZ0KiJHbVF2SEx0YWB1RLFEbMplY09iei1UZ410K0Yld18GawJGcixEei5GSONWWF9SS41EbDRWOslUO5RDTzJ1QmVnZjN1LWlXVBtmU0ZHZ&tablename=CJFQ2013";
        int i = url.indexOf("?");
        System.out.println(url.substring(0,i)+url.substring(i,url.length()));
    }
}
