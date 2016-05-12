package cn.wanghaomiao.xpath.model;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author 汪浩淼 [ et.tw@163.com ]
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
             res = xpathEva.xpathParser(xpath,elements);
        } catch (NoSuchAxisException e) {
            throw e;
        } catch (NoSuchFunctionException e) {
            throw e;
        } catch (Exception e){
            throw new XpathSyntaxErrorException("please check the xpath syntax");
        }
        return res;
    }
}
