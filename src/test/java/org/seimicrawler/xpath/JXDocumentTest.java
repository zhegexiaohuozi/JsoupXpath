package org.seimicrawler.xpath;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seimicrawler.xpath.exception.XpathSyntaxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private ClassLoader loader = getClass().getClassLoader();
    private Logger logger = LoggerFactory.getLogger(JXDocumentTest.class);

    @Before
    public void before() throws Exception {
        String html = "<html><body><script>console.log('aaaaa')</script><div class='test'>搜易贷致力于普惠金融，专注于互联网投资理财与小额贷款，搭建中国最大、用户体验最好的个人及中小企业的互联网信贷平台</div><div class='xiao'>Two</div></body></html>";
        underTest = JXDocument.create(html);
        if (doubanTest == null) {
            URL t = loader.getResource("d_test.html");
            assert t != null;
            File dBook = new File(t.toURI());
            String context = FileUtils.readFileToString(dBook, Charset.forName("utf8"));
            doubanTest = JXDocument.create(context);
        }
        custom = JXDocument.create("<li><b>性别：</b>男</li>");
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
        Assert.assertEquals(1, res.size());
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
            if (!n.isString()) {
                int index = n.asElement().siblingIndex();
                logger.info("index = {}",index);
            }
            logger.info(n.toString());
        }
    }

    /**
     * d_test.html 来源于 https://book.douban.com/tag/%E4%BA%92%E8%81%94%E7%BD%91
     *
     * 为了测试各种可能情况，ul[@class='subject-list']节点以及其下内容被复制了一份出来，并修改部分书名前缀为'T2-'以便区分
     */
    @DataProvider
    public static Object[][] dataOfXpathAndexpect() {
        return new Object[][] {
                { "//ul[@class='subject-list']/li[position()<3][last()]/div/h2/allText()", "黑客与画家 : 硅谷创业之父Paul Graham文集T2-黑客与画家 : 硅谷创业之父Paul Graham文集" },
                { "//ul[@class='subject-list']/li[first()]/div/h2/allText()", "失控 : 全人类的最终命运和结局T2-失控 : 全人类的最终命运和结局" },
                { "//ul[@class='subject-list']/li[./div/div/span[@class='pl']/num()>(1000+90*(2*50))][last()][1]/div/h2/allText()", "长尾理论长尾理论" },
                { "//ul[@class='subject-list']/li[self::li/div/div/span[@class='pl']/num()>10000][-1]/div/h2/allText()",   "长尾理论长尾理论" },
                { "//ul[@class='subject-list']/li[contains(self::li/div/div/span[@class='pl']//text(),'14582')]/div/h2//text()",   "黑客与画家 : 硅谷创业之父Paul Graham文集T2-黑客与画家 : 硅谷创业之父Paul Graham文集" },
                { "//ul[@class='subject-list']/li[contains(./div/div/span[@class='pl']//text(),'14582')]/div/h2//text()",   "黑客与画家 : 硅谷创业之父Paul Graham文集T2-黑客与画家 : 硅谷创业之父Paul Graham文集" },
                { "//*[@id=\"subject_list\"]/ul/li[2]/div[2]/h2/a//text()",   "黑客与画家 : 硅谷创业之父Paul Graham文集T2-黑客与画家 : 硅谷创业之父Paul Graham文集" },
                { "//ul[@class]",   3L },
                { "//a[@id]/@href",   "https://www.douban.com/doumail/" },
                { "//*[@id=\"subject_list\"]/ul[1]/li[8]/div[2]/div[2]/span[3]/num()",   "3734.0" },
                { "//a[@id]/@href | //*[@id=\"subject_list\"]/ul[1]/li[8]/div[2]/div[2]/span[3]/num()",   "https://www.douban.com/doumail/3734.0" },
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
        }else if (expect instanceof Number){
            long size = (long) expect;
            Assert.assertEquals(size,rs.size());
        }
    }

    @Test
    @DataProvider(value = {
            "//ul[@class='subject-list']/li[position()<3]"
    })
    public void testJXNode(String xpath) throws XpathSyntaxErrorException {
        logger.info("current xpath: {}" , xpath);
        List<JXNode> jxNodeList = doubanTest.selN(xpath);
        Set<String> expect = new HashSet<>();
        //第一个 ul 中的
        expect.add("失控 : 全人类的最终命运和结局");
        expect.add("黑客与画家 : 硅谷创业之父Paul Graham文集");
        //第二个 ul 中的
        expect.add("T2-失控 : 全人类的最终命运和结局");
        expect.add("T2-黑客与画家 : 硅谷创业之父Paul Graham文集");

        Set<String> res = new HashSet<>();
        for (JXNode node : jxNodeList) {
            if (!node.isString()) {
                String currentRes = StringUtils.join(node.sel("/div/h2/a//text()"), "");
                logger.info(currentRes);
                res.add(currentRes);
            }
        }
        Assert.assertEquals(expect, res);
    }

    @Test
    @DataProvider(value = {
            "//ul[@class='subject-list']"
    })
    public void testRecursionNode(String xpath) throws XpathSyntaxErrorException {
        logger.info("current xpath: {}" , xpath);
        List<JXNode> jxNodeList = doubanTest.selN(xpath);
        logger.info("size = {}",jxNodeList.size());
        // 有两个ul，下面的是为了测试特意复制添加的
        Assert.assertEquals(2, jxNodeList.size());
    }

    @Test
    @DataProvider(value = {
            "//body/div/div/h1/text()",
            "/body/div/div/h1/text()"
    })
    public void absolutePathTest(String xpath) throws XpathSyntaxErrorException {
        logger.info("current xpath: {}" , xpath);
        List<JXNode> jxNodeList = doubanTest.selN(xpath);
        logger.info("size = {}，res ={}",jxNodeList.size(),jxNodeList);
    }

    @Test
    public void testAs() throws XpathSyntaxErrorException {
        List<JXNode> jxNodeList = custom.selN("//b[contains(text(),'性别')]/parent::*/text()");
        Assert.assertEquals("男",StringUtils.join(jxNodeList,""));
        for (JXNode jxNode : jxNodeList) {
            logger.info(jxNode.toString());
        }
    }

    /**
     * fix https://github.com/zhegexiaohuozi/JsoupXpath/issues/33
     */
//    @Test
    public void testNotObj(){
        JXDocument doc = JXDocument.createByUrl("https://www.gxwztv.com/61/61514/");
//        List<JXNode> nodes = doc.selN("//*[@id=\"chapters-list\"]/li[@style]");
        List<JXNode> nodes = doc.selN("//*[@id=\"chapters-list\"]/li[not(@style)]");
        for (JXNode node:nodes){
            logger.info("r = {}",node);
        }
    }

    /**
     * fix https://github.com/zhegexiaohuozi/JsoupXpath/issues/34
     */
    @Test
    public void testAttrAtRoot(){
        String content = "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <a href=\"/124/124818/162585930.html\">第2章 神奇交流群</a>\n" +
                " </body>\n" +
                "</html>";
        JXDocument doc = JXDocument.create(content);
        List<JXNode> nodes = doc.selN("//@href");
        for (JXNode node:nodes){
            logger.info("r = {}",node);
        }
    }

    @Test
    public void testA(){
        String content = "<span style=\"color: #5191ce;\" >网页设计师</span>";
        JXDocument doc = JXDocument.create(content);
        List<JXNode> nodes = doc.selN("//*[text()='网页设计师']");
        for (JXNode node:nodes){
            logger.info("r = {}",node);
        }
    }

}
