

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 13-12-16 下午5:03
 */
public class DomParserTest {
    public static void main(String[] args) throws IOException, SAXException, DocumentException, ParserConfigurationException, XPathExpressionException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String body = "";
        try {
//            HttpHost target = new HttpHost("issues.apache.org", 443, "https");
//            HttpHost proxy = new HttpHost("127.0.0.1", 8087, "http");

//            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();

            HttpGet request = new HttpGet("http://www.w3school.com.cn/xpath/xpath_axes.asp");
            request.setConfig(config);

//            System.out.println("executing request to via " + proxy);
            CloseableHttpResponse response = httpclient.execute(request);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                Header[] headers = response.getAllHeaders();
                for (int i = 0; i<headers.length; i++) {
                    System.out.println(headers[i]);
                }
                System.out.println("----------------------------------------");
                if (entity != null) {
//                    body = EntityUtils.toString(entity);
                    body = new String(EntityUtils.toByteArray(entity),"gb2312");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        TagNode tagNode = new HtmlCleaner().clean(body);
        org.w3c.dom.Document document = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
        XPath xpath = XPathFactory.newInstance().newXPath();
        String res = (String) xpath.evaluate("//h3[contains(following-sibling::pre/text(),'step')]/text()",document, XPathConstants.NODESET);
        System.out.println(res);
    }
}
