package org.seimicrawler.xpath;
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

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * XPath提取后的
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2016/5/12.
 */
public class JXNode {
    private Object value;

    public JXNode(Object val){
        this.value = val;
    }

    public boolean isElement(){
        return value instanceof Element;
    }

    public Element asElement() {
        return (Element) value;
    }

    public boolean isString(){
        return value instanceof String;
    }

    public String asString(){
        return (String) value;
    }

    public boolean isNumber(){
        return value instanceof Number;
    }

    public Double asDouble(){
        return (Double) value;
    }

    public List<JXNode> sel(String xpath) {
        if (!isElement()){
            return null;
        }
        JXDocument doc = new JXDocument(new Elements(asElement()));
        return doc.selN(xpath);
    }

    public static JXNode create(Object val){
        return new JXNode(val);
    }

    @Override
    public String toString() {
        if (isElement()){
            return asElement().toString();
        }else {
            return String.valueOf(value);
        }
    }
}
