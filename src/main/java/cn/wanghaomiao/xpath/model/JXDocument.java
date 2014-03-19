package cn.wanghaomiao.xpath.model;

import cn.wanghaomiao.xpath.core.XpathEvaluator;
import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author 汪浩淼 [ et.tw@163.com ]
 * @since 14-3-19 上午9:29
 */
public class JXDocument {
    private Elements elements;
    private XpathEvaluator xpathEva = new XpathEvaluator();
    public JXDocument(Document doc){
        elements = doc.children();
    }
    public JXDocument(String html){
        elements = Jsoup.parse(html).children();
    }
    public JXDocument(Elements els){
        elements = els;
    }
    
    public List<Object> sel(String xpath) throws NoSuchAxisException, NoSuchFunctionException, XpathSyntaxErrorException {
        List<Object> res = null;
        try {
             res = xpathEva.evaluate(xpath,elements);
        } catch (NoSuchAxisException e) {
            throw e;
        } catch (NoSuchFunctionException e) {
            throw e;
        } catch (Exception e){
            e.printStackTrace();
            throw new XpathSyntaxErrorException("please check the xpath syntax");
        }
        return res;
    }
}
