package cn.wanghaomiao.xpath.model;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
   Copyright 2014 Wang Haomiao<et.tw@163.com>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
import cn.wanghaomiao.xpath.core.XpathEvaluator;
import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

/**
 * @author github.com/zhegexiaohuozi [seimimaster@gmail.com]
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
    
    public List<Object> sel(String xpath) throws XpathSyntaxErrorException {
        List<Object> res = new LinkedList<Object>();
        try {
             List<JXNode> jns = xpathEva.xpathParser(xpath,elements);
             for (JXNode j:jns){
                 if (j.isText()){
                     res.add(j.getTextVal());
                 }else {
                     res.add(j.getElement());
                 }
             }
        } catch (Exception e){
            String msg = "please check the xpath syntax";
            if (e instanceof NoSuchAxisException||e instanceof NoSuchFunctionException){
                msg = e.getMessage();
            }
            throw new XpathSyntaxErrorException(msg);
        }
        return res;
    }

    public List<JXNode> selN(String xpath) throws XpathSyntaxErrorException{
        try {
            return xpathEva.xpathParser(xpath,elements);
        }catch (Exception e){
            String msg = "please check the xpath syntax";
            if (e instanceof NoSuchAxisException||e instanceof NoSuchFunctionException){
                msg = e.getMessage();
            }
            throw new XpathSyntaxErrorException(msg);
        }
    }
    
    public Object selOne(String xpath) throws XpathSyntaxErrorException {
    	JXNode jxNode = selNOne(xpath);
    	if(jxNode != null) {
    		if (jxNode.isText()){
                return jxNode.getTextVal();
            }else {
                return jxNode.getElement();
            }
    	}
    	return null;
    }
    
    public JXNode selNOne(String xpath) throws XpathSyntaxErrorException {
    	List<JXNode> jxNodeList = selN(xpath);
    	if(jxNodeList != null && jxNodeList.size() > 0) {
    		return jxNodeList.get(0);
    	}
    	return null;
    }
}
