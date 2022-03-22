package org.seimicrawler.xpath.expr;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.seimicrawler.xpath.BaseTest;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author jiangyj
 * @version 1.0
 * @create 2021-12-06 15:03
 **/
public class ArrayExprTest extends BaseTest {

    private Elements root;
    private ClassLoader loader = getClass().getClassLoader();
    @Before
    public void init() throws Exception {
        //  https://book.douban.com/tag/%E4%BA%92%E8%81%94%E7%BD%91
        URL t = loader.getResource("d_test.html");
        assert t!=null;
        File dBook = new File(t.toURI());
        String context = FileUtils.readFileToString(dBook, Charset.forName("utf8"));
        root = Jsoup.parse(context).children();
    }

    @Test
    public void commonTest() {
        String xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li/a/text()";
        JXDocument jxDocument = JXDocument.create(root);
        List<JXNode> result = jxDocument.selN(xpath);
        Assert.assertEquals(10, result.size());
        Assert.assertEquals("豆瓣", result.get(0).asString());
        Assert.assertEquals("读书", result.get(1).asString());
        Assert.assertEquals("市集", result.get(9).asString());
    }


    @Test
    public void arrayTest() {
        String xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li[1]/a/text()";
        JXDocument jxDocument = JXDocument.create(root);
        List<JXNode> result = jxDocument.selN(xpath);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("豆瓣", result.get(0).asString());

        xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li[4]/a/text()";
        result = jxDocument.selN(xpath);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("音乐", result.get(0).asString());

        xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li[0]/a/text()";
        result = jxDocument.selN(xpath);
        Assert.assertEquals(0, result.size());


        xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li[-1]/a/text()";
        result = jxDocument.selN(xpath);
        Assert.assertEquals(0, result.size());
    }


    @Test
    public void positionTest() {
        String xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li[position() < 5]/a/text()";
        JXDocument jxDocument = JXDocument.create(root);
        List<JXNode> result = jxDocument.selN(xpath);
        Assert.assertEquals(4, result.size());
        Assert.assertEquals("豆瓣", result.get(0).asString());

        xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li[position() < -1]/a/text()";
        result = jxDocument.selN(xpath);
        Assert.assertEquals(0, result.size());

        xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li[position() < 100]/a/text()";
        result = jxDocument.selN(xpath);
        Assert.assertEquals(10, result.size());
        Assert.assertEquals("音乐", result.get(3).asString());
    }

    @Test
    public void complexArrayTest() {
        // target跳过第二条读书，结果排序：豆瓣、电影、音乐、同城..
        String xpath = "//div[@id='db-global-nav']/div/div[@class='global-nav-items']/ul/li[child::a[contains(@target, '_blank')]][3]/a/text()";
        JXDocument jxDocument = JXDocument.create(root);
        List<JXNode> result = jxDocument.selN(xpath);
        for (JXNode jxNode : result){
            System.out.println(jxNode.asString());
        }
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("音乐", result.get(0).asString());
    }

}
