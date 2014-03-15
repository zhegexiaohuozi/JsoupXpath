package cn.wanghaomiao.xpath.test;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-3-3 上午10:00
 */
public class LoginTest {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String qurey="trnsys";
        HttpPost post = new HttpPost("https://ssl.hrbeu.edu.cn/por/login_psw.csp");
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("svpn_name","2008096108"));
        params.add(new BasicNameValuePair("svpn_password","whm250"));
        params.add(new BasicNameValuePair("svpn_rand_code",""));
        post.setEntity(new UrlEncodedFormEntity(params));
        httpclient.execute(post);

        HttpGet httpGet = new HttpGet("https://ssl.hrbeu.edu.cn/web/1/http/0/lib.hrbeu.edu.cn/");
        httpclient.execute(httpGet);

//        ArrayList<NameValuePair> seaparams = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("ConfigFile","SCDB.xml"));
//        params.add(new BasicNameValuePair("DBViewType","FullText"));
//        params.add(new BasicNameValuePair("DbCatalog","中国学术文献网络出版总库"));
//        params.add(new BasicNameValuePair("DbPrefix","SCDB"));
//        params.add(new BasicNameValuePair("NaviCode","A,B,C,D,E,F,G,H,I,J,A005_7A"));
//        params.add(new BasicNameValuePair("NaviField","专题子栏目代码"));
//        params.add(new BasicNameValuePair("PageName","ASP.brief_libraryresult_aspx"));
//        params.add(new BasicNameValuePair("SourceTypeCode","undefined"));
//        params.add(new BasicNameValuePair("db_value","CJFQ,CDFD,CMFD,CPFD,CCND,CBFD,SCPD,SCSD,SNAD,SOSD,CYFD,CJSF,CSYD,CRFD1,ECPH1,SCSF,CDSF,CMSF,SSBD,GXBD,HBRD,CRLD,FCJD,WGCY,JZYS,SSJD,STJD"));
//        params.add(new BasicNameValuePair("his","1"));
//        params.add(new BasicNameValuePair("orderby","(发表时间,'TIME') DESC"));
//        params.add(new BasicNameValuePair("txt_1_sel","题名,作者,机构,单位,学位授予单位,作者机构,文献来源"));
//        params.add(new BasicNameValuePair("txt_1_value1",qurey));
//        HttpPost search = new HttpPost("https://ssl.hrbeu.edu.cn/web/0/http/2/elib.cnki.net/grid2008/request/search.aspx");
//        search.setEntity(new UrlEncodedFormEntity(seaparams));
//        HttpResponse response1 = httpclient.execute(search);
//        String nextLink = EntityUtils.toString(response1.getEntity());
//        HttpResponse response = httpclient.execute(new HttpGet("https://ssl.hrbeu.edu.cn/web/0/http/1/elib.cnki.net/grid2008/DataCenter/DoGridTable.aspx?action=grid&pagename="+nextLink));
        HttpResponse response = httpclient.execute(new HttpGet("https://ssl.hrbeu.edu.cn/web/1/http/1/elib.cnki.net/grid2008/jump.aspx?url=http://epub.cnki.net/kns/loginid.aspx?uid={UID}&p=download.aspx?filename=4IUV2o2dih0KDF3UXVDOrdHSWZUU5U2LodTSnRkWulVZ5dXdFFWYZ9SewUEMu90TGhUYUVXSMZWZRRkbNtkexEWcId1SF9yLilFaIJDO1FEUQRES3kndktiUCR1ZydEcW5kUn9iZYt0dCJGeDFHaRlWSzt2V1hEc&tablename=CJFQ2013"));
        String res=EntityUtils.toString(response.getEntity());
        System.out.println(res);
    }
}
