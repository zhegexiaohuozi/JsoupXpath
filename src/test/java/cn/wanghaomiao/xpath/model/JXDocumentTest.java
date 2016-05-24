package cn.wanghaomiao.xpath.model;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.List;

/**
 * JXDocument Tester.
 *
 * @author <et.tw@163.com>
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(DataProviderRunner.class)
public class JXDocumentTest {

    private JXDocument underTest;

    private JXDocument doubanTest;

    @Before
    public void before() throws Exception {
        String html = "<html><body><script>console.log('aaaaa')</script><div class='test'>搜易贷致力于普惠金融，专注于互联网投资理财与小额贷款，搭建中国最大、用户体验最好的个人及中小企业的互联网信贷平台</div><div class='xiao'>Two</div></body></html>";
        underTest = new JXDocument(html);
        if (doubanTest == null){
            Document doc = Jsoup.connect("https://book.douban.com/subject_search?start=15&search_text=java&cat=1001").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0").get();
            doubanTest = new JXDocument(doc);
        }
    }
    /**
     * Method: sel(String xpath)
     */
    @Test
    public void testSel() throws Exception {
        String xpath="//script[1]/text()";
        List<Object> res = underTest.sel(xpath);
        Assert.assertNotNull(res);
        Assert.assertTrue(res.size()>0);
        System.out.println(StringUtils.join(res,","));
    }

    @Test
    public void testNotMatchFilter() throws Exception {
        String xpath="//div[@class!~'xiao']/text()";
        List<Object> res = underTest.sel(xpath);
        System.out.println(StringUtils.join(res,","));
    }

    @Test
    @DataProvider(value = {
            "//a/@href",
            "//div[@class='paginator']/span[@class='next']/a/@href",
            "//ul[@class='subject-list']/li[position()<3]/div/h2/allText()",
            "//ul[@class='subject-list']/li[first()]/div/h2/allText()",
            "//ul[@class='subject-list']/li[./div/div/span[@class='pl']/num()>900]/div/h2/allText()", //查找评论大于1000的条目（当然只是为了演示复杂xpath了，谓语中可以各种嵌套，这样才能测试的更全面）
            "//ul[@class='subject-list']/li[self::li/div/div/span[@class='pl']/num()>900]/div/h2/allText()",
            "//*[@id='content']/div/div[1]/ul/li[14]/div[2]/h2/a/text()" //chrome拷贝
    })
    public void testXpath(String xpath) throws NoSuchFunctionException, XpathSyntaxErrorException, NoSuchAxisException {
        System.out.println("current xpath:"+xpath);
        List<Object> rs = doubanTest.sel(xpath);
        for (Object o:rs){
            if (o instanceof Element){
                int index = ((Element) o).siblingIndex();
                System.out.println(index);
            }
            System.out.println(o.toString());
        }
    }
}
