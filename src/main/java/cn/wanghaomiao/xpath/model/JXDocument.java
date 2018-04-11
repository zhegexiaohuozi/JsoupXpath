package cn.wanghaomiao.xpath.model;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.exception.XpathSyntaxErrorException;

import java.util.LinkedList;
import java.util.List;

/*
   Copyright 2014 Wang Haomiao<seimimaster@gmail.com>

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

/**
 * 下一个版本将移除，推荐使用 {@link org.seimicrawler.xpath.JXDocument}
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 */
@Deprecated
public class JXDocument {
    private org.seimicrawler.xpath.JXDocument jxDoc;
    public JXDocument(Document doc){
        jxDoc = org.seimicrawler.xpath.JXDocument.create(doc);
    }
    public JXDocument(String html){
        jxDoc = org.seimicrawler.xpath.JXDocument.create(html);
    }
    public JXDocument(Elements els){
        jxDoc = org.seimicrawler.xpath.JXDocument.create(els);
    }
    
    public List<Object> sel(String xpath) throws XpathSyntaxErrorException {
        return jxDoc.sel(xpath);
    }

    public List<JXNode> selN(String xpath) throws XpathSyntaxErrorException{
        List<JXNode> finalRes = new LinkedList<>();
        List<org.seimicrawler.xpath.JXNode> jxNodeList = jxDoc.selN(xpath);
        for (org.seimicrawler.xpath.JXNode n:jxNodeList){
            if (n.isString()){
                finalRes.add(JXNode.t(n.asString()));
            }else {
                finalRes.add(JXNode.e(n.asElement()));
            }
        }
        return finalRes;
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
