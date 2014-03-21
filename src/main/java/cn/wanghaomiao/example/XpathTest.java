package cn.wanghaomiao.example;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
        String x7 = "//div[@id='post_list']/div[position()<3]/div/h3/allText()";
        String x8 = "//div[@id='post_list']/div[first()]/div/h3/allText()";
        String x9 = "//div[@id='post_list']/div[1]/div/h3/allText()";
//        String x9 = "//div[@id='post_list']/div[last()]/div/h3/allText()";
        //杀器，查找评论大于1000的条目（当然只是为了演示复杂xpath了，谓语中可以各种嵌套，这样才能测试的更全面嘛）
        String x10 = "//div[@id='post_list']/div[./div/div/span[@class='article_view']/a/num()>1000]/div/h3/allText()";
        //轴的支持，必须接近完美
        String x11 = "//div[@id='post_list']/div[self::div/div/div/span[@class='article_view']/a/num()>1000]/div/h3/allText()";
        String x12 = "//div[@id='post_list']/div[2]/div/p/preceding-sibling::h3/allText()";
        String x13 = "//div[@id='post_list']/div[2]/div/p/preceding-sibling::h3/allText()|//div[@id='post_list']/div[1]/div/h3/allText()";
        Document doc = Jsoup.connect("http://www.cnblogs.com/").get();
        JXDocument jxDocument = new JXDocument(doc);
        List<Object> rs = jxDocument.sel(x13);
        for (Object o:rs){
            if (o instanceof Element){
                int index = ((Element) o).siblingIndex();
                System.out.println(index);
            }
            System.out.println(o.toString());
        }
    }
}
