package cn.wanghaomiao.xpath.test;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-2-10 上午10:52
 */
public class HttpTest {
     public static void main(String[] args) throws IOException {
//         String res=Request.Get("").addHeader("Cookie","TWFID=246895e43bd7fa46; collection=%7Bpage_state%3A%27started%27%2Cauto_login_count%3A0%2Cneed_ist_cscm%3A%27-1%27%2CscacheUseable%3A0%2CAppCount%3A0%7D; VisitTimes=0; haveLogin=1; LoginMode=2; websvr_cookie=1391998853223140")
//                .addHeader("Referer","https://ssl.hrbeu.edu.cn/web/1/http/1/elib.cnki.net/grid2008/brief/Libraryresult.aspx?&PageName=&DBViewType=FullText&DbCatalog=%u4E2D%u56FD%u5B66%u672F%u6587%u732E%u7F51%u7EDC%u51FA%u7248%u603B%u5E93&DbPrefix=SCDB&ConfigFile=%u4E2D%u56FD%u5B66%u672F%u6587%u732E%u7F51%u7EDC%u51FA%u7248%u603B%u5E93.xml&orderby=(%E5%8F%91%E8%A1%A8%E6%97%B6%E9%97%B4%2C'TIME')%20DESC&txt_1_sel=%E9%A2%98%E5%90%8D%2C%E4%BD%9C%E8%80%85%2C%E6%9C%BA%E6%9E%84%2C%E5%8D%95%E4%BD%8D%2C%E5%AD%A6%E4%BD%8D%E6%8E%88%E4%BA%88%E5%8D%95%E4%BD%8D%2C%E4%BD%9C%E8%80%85%E6%9C%BA%E6%9E%84%2C%E6%96%87%E7%8C%AE%E6%9D%A5%E6%BA%90&txt_1_value1=%E6%9C%BA%E5%99%A8%E5%AD%A6%E4%B9%A0&sTab=quick2&navicode=A,B,C,D,E,F,G,H,I,J,A005_7A&plkind=Unit_org&targetname=dx0437")
//                .execute().returnContent().asString();
//         System.out.println(res);
         CloseableHttpClient httpclient = HttpClients.createDefault();
         String uri="/web/1/http/1/elib.cnki.net/grid2008/jump.aspx?url=http://epub.cnki.net/kns/loginid.aspx?uid={UID}&p=download.aspx?filename=G52T5U0bll0T2FFTBBVe4VUeOdUcxdGc5hnM21UVo5mVWZlU0s2SXZ0KiJHbVF2SEx0YWB1RLFEbMplY09iei1UZ410K0Yld18GawJGcixEei5GSONWWF9SS41EbDRWOslUO5RDTzJ1QmVnZjN1LWlXVBtmU0ZHZ&tablename=CJFQ2013";
         String url="https://ssl.hrbeu.edu.cn"+uri;
         String body = "";
         try {
//            HttpHost target = new HttpHost("ssl.hrbeu.edu.cn", 443, "https");
//            HttpHost proxy = new HttpHost("127.0.0.1", 8087, "http");

//            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//             RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
            int firstIndex = url.indexOf("?");

             HttpGet request = new HttpGet(url.substring(0,firstIndex+1)+URLEncoder.encode(url.substring(firstIndex+1,url.length())));
             request.addHeader("Cookie","TWFID=246895e43bd7fa46; collection=%7Bpage_state%3A%27started%27%2Cauto_login_count%3A0%2Cneed_ist_cscm%3A%27-1%27%2CscacheUseable%3A0%2CAppCount%3A0%7D; VisitTimes=0; haveLogin=1; LoginMode=2; websvr_cookie=1391998853223140");
             System.out.println(URLEncoder.encode(uri));

//             request.setConfig(config);

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
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             httpclient.close();
         }
         System.out.println(body);

     }
}
