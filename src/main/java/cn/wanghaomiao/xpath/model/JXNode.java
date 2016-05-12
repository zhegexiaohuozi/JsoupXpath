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
import org.jsoup.select.Elements;

import java.util.List;

/**
 * XPath提取后的
 * @author 汪浩淼 et.tw@163.com
 * @since 2016/5/12.
 */
public class JXNode {
    private Elements elements;
    private boolean isText;
    private String textVal;

    public Elements getElements() {
        return elements;
    }

    public JXNode setElements(Elements elements) {
        this.elements = elements;
        return this;
    }

    public boolean isText() {
        return isText;
    }

    public JXNode setText(boolean text) {
        isText = text;
        return this;
    }

    public String getTextVal() {
        return textVal;
    }

    public JXNode setTextVal(String textVal) {
        this.textVal = textVal;
        return this;
    }

    public List<JXNode> xpath(String xpath){
        if (elements==null||elements.size()<=0){
            return null;
        }
        JXDocument doc = new JXDocument(elements);
        return doc.xpath(xpath);
    }
}
