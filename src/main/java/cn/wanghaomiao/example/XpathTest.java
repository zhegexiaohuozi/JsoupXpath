package cn.wanghaomiao.example;

import cn.wanghaomiao.xpath.core.XpathEvaluator;
import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.Node;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * @author 汪浩淼 [ et.tw@163.com ]
 * @since 14-3-19 上午11:03
 */
public class XpathTest {
    public static void main(String[] args) throws IOException, NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException {
        String x1 = "//a/@href";
        String x2 = "//div[@class='BlogStat']/a/span/text()";
        List<Node> ndlist = new XpathEvaluator().getXpathNodeTree(x2);
        JSONObject j = new JSONObject();
        j.put("ns",ndlist);
        System.out.println(j.toJSONString());
//        for (Node n:ndlist){
//            
//        }
//        Document doc = Jsoup.connect("http://my.oschina.net/feichexia/blog/196575").get();
        String body = HttpUtil.getBody("http://my.oschina.net/feichexia/blog/196575");
        JXDocument jxDocument = new JXDocument(body);
        List<Object> rs = jxDocument.sel(x2);
        for (Object o:rs){
            System.out.println(o.toString());
        }
    }
}
