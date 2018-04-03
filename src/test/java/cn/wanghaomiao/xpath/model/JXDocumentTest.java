package cn.wanghaomiao.xpath.model;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * JXDocument Tester.
 *
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @version 1.0
 */
@RunWith(DataProviderRunner.class)
public class JXDocumentTest {

    private JXDocument underTest;

    private JXDocument doubanTest;

    private JXDocument custom;

    private Logger logger = LoggerFactory.getLogger(JXDocumentTest.class);

    @Before
    public void before() throws Exception {
        String html = "<html><body><script>console.log('aaaaa')</script><div class='test'>搜易贷致力于普惠金融，专注于互联网投资理财与小额贷款，搭建中国最大、用户体验最好的个人及中小企业的互联网信贷平台</div><div class='xiao'>Two</div></body></html>";
        underTest = new JXDocument(html);
        if (doubanTest == null) {
            URL t = Resources.getResource("d_test.html");
            File dBook = new File(t.toURI());
            String context = Files.toString(dBook, Charset.forName("utf8"));
            doubanTest = new JXDocument(context);
        }
        custom = new JXDocument("<li><b>性别：</b>男</li>");
    }

    /**
     * Method: sel(String xpath)
     */
    @Test
    public void testSel() throws Exception {
        String xpath = "//script[1]/text()";
        List<Object> res = underTest.sel(xpath);
        Assert.assertNotNull(res);
        Assert.assertTrue(res.size() > 0);
        logger.info(StringUtils.join(res, ","));
    }

    @Test
    public void testNotMatchFilter() throws Exception {
        String xpath = "//div[@class!~'xiao']/text()";
        List<Object> res = underTest.sel(xpath);
        Assert.assertTrue(res.size() == 1);
        logger.info(StringUtils.join(res, ","));
    }

    @Test
    @DataProvider(value = {
            "//a/@href",
            "//div[@class='paginator']/span[@class='next']/a/@href",
    })
    public void testXpath(String xpath) throws XpathSyntaxErrorException {
        logger.info("current xpath: {}" , xpath);
        List<JXNode> rs = doubanTest.selN(xpath);
        for (JXNode n : rs) {
            if (!n.isText()) {
                int index = n.getElement().siblingIndex();
                logger.info("index = {}",index);
            }
            logger.info(n.toString());
        }
    }

    @DataProvider
    public static Object[][] dataOfXpathAndexpect() {
        return new Object[][] {
                { "//ul[@class='subject-list']/li[position()<3][last()]/div/h2/allText()", "黑客与画家 : 硅谷创业之父Paul Graham文集T2-黑客与画家 : 硅谷创业之父Paul Graham文集" },
                { "//ul[@class='subject-list']/li[first()]/div/h2/allText()", "失控 : 全人类的最终命运和结局T2-失控 : 全人类的最终命运和结局" },
                { "//ul[@class='subject-list']/li[./div/div/span[@class='pl']/num()>10000][last()][1]/div/h2/allText()", "长尾理论长尾理论" },
                { "//ul[@class='subject-list']/li[self::li/div/div/span[@class='pl']/num()>10000][-1]/div/h2/allText()",   "长尾理论长尾理论" },
                { "//ul[@class='subject-list']/li[contains(self::li/div/div/span[@class='pl']//text(),'14582')]/div/h2//text()",   "黑客与画家 : 硅谷创业之父Paul Graham文集T2-黑客与画家 : 硅谷创业之父Paul Graham文集" },
                { "//ul[@class='subject-list']/li[contains(./div/div/span[@class='pl']//text(),'14582')]/div/h2//text()",   "黑客与画家 : 硅谷创业之父Paul Graham文集T2-黑客与画家 : 硅谷创业之父Paul Graham文集" },
                { "//*[@id=\"subject_list\"]/ul/li[2]/div[2]/h2/a//text()",   "黑客与画家 : 硅谷创业之父Paul Graham文集T2-黑客与画家 : 硅谷创业之父Paul Graham文集" },
        };
    }

    @UseDataProvider("dataOfXpathAndexpect")
    @Test
    public void testXpathAndAssert(String xpath,Object expect) throws XpathSyntaxErrorException {
        logger.info("current xpath: {}" , xpath);
        List<JXNode> rs = doubanTest.selN(xpath);
        if (expect instanceof String){
            String res = StringUtils.join(rs,"");
            logger.info(res);
            Assert.assertEquals(expect,res);
        }else {
            logger.info(" -- ");
        }

    }

    @Test
    @DataProvider(value = {
            "//ul[@class='subject-list']/li"
    })
    public void testJXNode(String xpath) throws XpathSyntaxErrorException {
        logger.info("current xpath: {}" , xpath);
        List<JXNode> jxNodeList = doubanTest.selN(xpath);
        for (JXNode node : jxNodeList) {
            if (!node.isText()) {
                logger.info(StringUtils.join(node.sel("/div/h2/a/text()"), ""));
            }
        }
    }

    @Test
    @DataProvider(value = {
            "//ul[@class='subject-list']"
    })
    public void testRecursionNode(String xpath) throws XpathSyntaxErrorException {
        logger.info("current xpath: {}" , xpath);
        List<JXNode> jxNodeList = doubanTest.selN(xpath);
        logger.info("size = {}",jxNodeList.size());
    }

    @Test
    public void testAs() throws XpathSyntaxErrorException {
        List<JXNode> jxNodeList = custom.selN("//b[contains(text(),'性别')]/parent::*/text()");
        Assert.assertEquals("男",StringUtils.join(jxNodeList,""));
        for (JXNode jxNode : jxNodeList) {
            logger.info(jxNode.getTextVal());
        }
    }

}
