package cn.wanghaomiao.xpath.util;
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

import java.util.Objects;

/**
 * @author: github.com/zhegexiaohuozi seimimaster@gmail.com
 * Date: 14-3-15
 */
public class CommonUtil {

    public static int getIndexInContext(Element el,Elements context){
        for (int i = 0;i<context.size();i++){
            Element tmp = context.get(i);
            if (Objects.equals(tmp,el)){
                return i+1;
            }
        }
        return Integer.MIN_VALUE;
    }

     public static Elements followingSibling(Element el){
        Elements rs = new Elements();
        Element tmp = el.nextElementSibling();
        while (tmp!=null){
            rs.add(tmp);
            tmp = tmp.nextElementSibling();
        }
        if (rs.size() > 0){
            return rs;
        }
        return null;
    }

    public static Elements precedingSibling(Element el){
        Elements rs = new Elements();
        Element tmp = el.previousElementSibling();
        while (tmp!=null){
            rs.add(tmp);
            tmp = tmp.previousElementSibling();
        }
        if (rs.size() > 0){
            return rs;
        }
        return null;
    }
}
