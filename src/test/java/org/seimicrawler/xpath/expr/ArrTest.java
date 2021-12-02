package org.seimicrawler.xpath.expr;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Ignore;
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
 * @create 2021-12-02 16:14
 **/
public class ArrTest extends BaseTest {

    private Elements root;
    private ClassLoader loader = getClass().getClassLoader();

    @Before
    public void init() throws Exception {
        //  https://book.douban.com/tag/%E4%BA%92%E8%81%94%E7%BD%91
        URL t = loader.getResource("arr_test.html");
        assert t != null;
        File dBook = new File(t.toURI());
        String context = FileUtils.readFileToString(dBook, Charset.forName("utf8"));
        root = Jsoup.parse(context).children();
    }

    @Test
    @Ignore
    public void a1() {
        for (int i = 0; i < 20; i++) {
            aaa();
        }
    }


    @Test
    public void aaa() {
        int maxNum = 1;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < maxNum; i++) {
            commonExpr();
        }
        logger.info("commonExpr size {} cost {}ms", maxNum, System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < maxNum; i++) {
            selectOneExpr();
        }
        logger.info("selectOneExpr() size {} cost {}ms", maxNum, System.currentTimeMillis() - startTime);

//        startTime = System.currentTimeMillis();
//        for (int i = 0; i < maxNum; i++) {
//            arrExpr();
//        }
//        logger.info("arrExpr size {} cost {}ms", maxNum, System.currentTimeMillis() - startTime);
    }


    @Test
    @Ignore
    public void commonExpr() {
        long startTime = System.currentTimeMillis();
        String xpath = "//div[child::h1[contains(text(),'t2')]]/h2/text()";
        JXDocument jxDocument = JXDocument.create(root);
        List<JXNode> result = jxDocument.selN(xpath);
//        for (JXNode jxNode : result) {
//            logger.info("content {}", jxNode.asString());
//        }
//        logger.info("size {} cost {}ms", result.size(), System.currentTimeMillis() - startTime);
    }

    @Test
    @Ignore
    public void selectOneExpr() {
        long startTime = System.currentTimeMillis();
        String xpath = "//div[child::h1[contains(text(),'t2')]]/h2/text()";
        JXDocument jxDocument = JXDocument.create(root);
        JXNode result = jxDocument.selNOne(xpath);
//        logger.info("content {}", result.asString());
//        logger.info("size {} cost {}ms", 1, System.currentTimeMillis() - startTime);
    }

    @Test
    @Ignore
    public void arrExpr() {
        long startTime = System.currentTimeMillis();
        String xpath = "//div[child::h1[contains(text(),'t2')]][0]/h2/text()";
        JXDocument jxDocument = JXDocument.create(root);
        List<JXNode> result = jxDocument.selN(xpath);
//        for (JXNode jxNode : result) {
//            logger.info("content {}", jxNode.asString());
//        }
//        logger.info("size {} cost {}ms", result.size(), System.currentTimeMillis() - startTime);
    }



//    @Test
//    public void positionExpr() {
//        long startTime = System.currentTimeMillis();
//        String xpath = "//div[child::h1[contains(text(),'t2')] and position() < 2]/h2/text()";
//        JXDocument jxDocument = JXDocument.create(root);
//        List<JXNode> result = jxDocument.selN(xpath);
//        logger.info("size {} cost {}ms", result.size(), System.currentTimeMillis() - startTime);
//    }

}
