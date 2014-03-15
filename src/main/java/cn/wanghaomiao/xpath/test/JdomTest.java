package cn.wanghaomiao.xpath.test;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-1-22 下午4:05
 */
public class JdomTest {
    public static void main(String[] args) throws JDOMException, IOException {
        String content="<body><a>tt</a><b><p>pp:6<11</b></body>";
        Document document = new SAXBuilder().build(new File("e:\\stdout.xml"));
        List<Element> els = (List<Element>) XPath.selectNodes(document, "//b/text()");
        System.out.println(StringUtils.join(els,""));
    }
}
