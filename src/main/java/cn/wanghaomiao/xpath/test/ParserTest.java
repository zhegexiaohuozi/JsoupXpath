package cn.wanghaomiao.xpath.test;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.dom.DOMElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 13-12-16 下午5:03
 */
public class ParserTest {
    


    public String getXml(String html){
        org.jsoup.nodes.Document htmlDoc = Jsoup.parse(html);
        org.dom4j.Document document = DocumentHelper.createDocument();
        org.dom4j.Element domEl = new DOMElement("root");
        pakEle(htmlDoc,domEl);
        document.add(domEl);
        document.setXMLEncoding("utf8");
        return document.asXML();
    }

    public void pakEle(Element jel,org.dom4j.Element domEl){
        if (jel.children()!=null&&jel.children().size()>0){
             for (Element elt:jel.children()){
                 org.dom4j.Element domtem=domEl.addElement(elt.tagName());
                 for (Attribute attr:elt.attributes()){
                     domtem.addAttribute(attr.getKey(), attr.getValue());
                 }
                 if (elt.hasText()){
                     domtem.setData(org.apache.commons.lang.StringEscapeUtils.escapeHtml(elt.ownText()));
                 }
                 pakEle(elt,domtem);
             }
        }
    }
    
    public static void main(String[] args) throws IOException, DocumentException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String body = "";
        String body2 = "";
        try {
//            HttpHost target = new HttpHost("issues.apache.org", 443, "https");
//            HttpHost proxy = new HttpHost("127.0.0.1", 8087, "http");

//            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();

            HttpGet request = new HttpGet("http://data.house.sina.com.cn/gz103134/xinxi/?wt_source=data_daohang_01_gz");
            request.setConfig(config);

            body2 =Request.Get("http://data.house.sina.com.cn/gz103134/xinxi/?wt_source=data_daohang_01_gz")
                    .connectTimeout(1000).socketTimeout(1000).execute().returnContent().asString();

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
                byte[] data = EntityUtils.toByteArray(entity);
                Document doctmp = Jsoup.parse(new String(data));
                String content = doctmp.select("meta[http-equiv=Content-Type]").attr("content");
                if (StringUtils.isNotBlank(content)&&content.contains("charset")){
                    String charset = content.split(";")[1].split("=")[1];
                    if (entity != null) {
                        body = new String(data,charset);
                    }
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
        Document doc = Jsoup.parse(body);
        Elements elements = doc.select("div[class=pt10 pb10 pr20 pl20] > ul > li");
        System.out.println(elements.html());

    }
}
