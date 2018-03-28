package cn.wanghaomiao.xpath.model;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
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
@PowerMockRunnerDelegate(DataProviderRunner.class)
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
        logger.info(StringUtils.join(res, ","));
    }

    @Test
    @DataProvider(value = {
            "//a/@href",
            "//div[@class='paginator']/span[@class='next']/a/@href",
            "//ul[@class='subject-list']/li[position()<3]/div/h2/allText()",
            "//ul[@class='subject-list']/li[first()]/div/h2/allText()",
            "//ul[@class='subject-list']/li[./div/div/span[@class='pl']/num()>10000]/div/h2/allText()", //查找评论大于10000的条目（当然只是为了演示复杂xpath了，谓语中可以各种嵌套，这样才能测试的更全面）
            "//ul[@class='subject-list']/li[self::li/div/div/span[@class='pl']/num()>10000]/div/h2/allText()",
            "//*[@id='content']/div/div[1]/ul/li[14]/div[2]/h2/a/text()" //chrome拷贝
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
        List<JXNode> jxNodeList = custom.selN("//b[text()='性别：']/parent::*/text()");
        for (JXNode jxNode : jxNodeList) {
            logger.info(jxNode.getTextVal());
        }
    }

}
