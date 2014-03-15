package cn.wanghaomiao.xpath.test;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-2-17 下午4:17
 */
public class MyHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8887), 0);
        server.createContext("/", new MyResponseHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("OK");
    }

    public static class MyResponseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            InputStream in = t.getRequestBody();
            Headers headers=t.getRequestHeaders();
            StringBuilder sb = new StringBuilder("");
            System.out.println(t.getRequestMethod());
            System.out.println(t.getRequestURI().toString());
            for (String k:headers.keySet()){
                String tmp=k+":"+ StringUtils.join(headers.get(k).toArray());
                System.out.println(tmp);
                sb.append(tmp+"\\n");
            }
            InputStreamReader ir=new InputStreamReader(in);
            BufferedReader br = new BufferedReader(ir);
            String res=br.readLine();
            sb.append(res);
            while (res!=null){
                System.out.println(res);
                res = br.readLine();
                sb.append(res);
            }
            t.sendResponseHeaders(200,sb.toString().length());
            t.getResponseBody().write(sb.toString().getBytes());
            t.close();
        }
    }
}
