package cn.wanghaomiao.xpath.util;

import org.apache.commons.lang.StringUtils;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-2-7 下午2:22
 */
public class HttpUtil {
    public static String getBody(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String body = "";
        try {
//            HttpHost target = new HttpHost("issues.apache.org", 443, "https");
//            HttpHost proxy = new HttpHost("127.0.0.1", 8087, "http");

//            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();

            HttpGet request = new HttpGet(url);
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
                    if (entity.getContentEncoding()!=null){
                        body = EntityUtils.toString(entity);
                    }else {
                        byte[] data = EntityUtils.toByteArray(entity);
                        Document doctmp = Jsoup.parse(new String(data));
                        String content = doctmp.select("meta[http-equiv=Content-Type]").attr("content");
                        if (StringUtils.isNotBlank(content)&&content.contains("charset")){
                            String charset = content.split(";")[1].split("=")[1];
                            body = new String(data, charset);
                        }else {
                            body = new String(data);
                        }
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
        return body;
    }
}
