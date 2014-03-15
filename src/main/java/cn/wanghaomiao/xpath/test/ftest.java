package cn.wanghaomiao.xpath.test;
import cn.wanghaomiao.xpath.util.HttpUtil;
import org.jaxen.JaxenException;

import java.io.IOException;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-1-23 下午4:29
 */
public class ftest {
    public static void main(String[] args) throws JaxenException, IOException {
        String res = HttpUtil.getBody("http://src.house.sina.com.cn/imp/imp/deal/55/15/d/009882f77f5156a56f304b0eecd_p7_mk7_wm47.jpg");
        System.out.println(res);
    }
}
