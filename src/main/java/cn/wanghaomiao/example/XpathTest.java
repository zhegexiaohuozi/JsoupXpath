package cn.wanghaomiao.example;

import cn.wanghaomiao.xpath.core.XpathEvaluator;
import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.Node;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

/**
 * @author 汪浩淼 [ et.tw@163.com ]
 * @since 14-3-19 上午11:03
 */
public class XpathTest {
    public static void main(String[] args) throws IOException, NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException {
        String x1 = "//a/@href";
//        String x2 = "//div[@id='paging_block']/div/a[text()='Next >']/@href";
        String x2 = "//div[@id='paging_block']/div/a[text()*='Next']/@href";
        String x3 = "//h1/text()";
        String x4 = "//h1/allText()";
        String x5 = "//h1//text()";
        String x6 = "//div/a";
        String x7 = "//div[@id='post_list']/div[position()<3]/div/div/h3/allText()";
        List<Node> ndlist = new XpathEvaluator().getXpathNodeTree(x7);
        JSONObject j = new JSONObject();
        j.put("ns",ndlist);
        System.out.println(j.toJSONString());
        Document doc = Jsoup.connect("http://www.cnblogs.com/").get();
        JXDocument jxDocument = new JXDocument(doc);
        List<Object> rs = jxDocument.sel(x7);
        for (Object o:rs){
            System.out.println(o.toString());
        }
    }
}
