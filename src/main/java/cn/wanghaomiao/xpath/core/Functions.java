package cn.wanghaomiao.xpath.core;
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
import cn.wanghaomiao.xpath.model.JXNode;
import cn.wanghaomiao.xpath.util.CommonUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * xpath解析器的支持的全部函数集合，如需扩展按形式添加即可
 * @author: github.com/zhegexiaohuozi [seimimaster@gmail.com]
 * Date: 14-3-15
 */
public class Functions {
    /**
     * 只获取节点自身的子文本
     * @param context
     * @return
     */
    public List<JXNode> text(Elements context){
        List<JXNode> res = new LinkedList<JXNode>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                if (e.nodeName().equals("script")){
                    res.add(JXNode.t(e.data()));
                }else {
                    res.add(JXNode.t(e.ownText()));
                }
            }
        }
        return res;
    }

    /**
     * 递归获取节点内全部的纯文本
     * @param context
     * @return
     */
    public List<JXNode> allText(Elements context){
        List<JXNode> res = new LinkedList<JXNode>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(JXNode.t(e.text()));
            }
        }
        return res;
    }

    /**
     * 获取全部节点的内部的html
     * @param context
     * @return
     */
    public List<JXNode> html(Elements context){
        List<JXNode> res = new LinkedList<JXNode>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(JXNode.t(e.html()));
            }
        }
        return res;
    }

    /**
     * 获取全部节点的 包含节点本身在内的全部html
     * @param context
     * @return
     */
    public List<JXNode> outerHtml(Elements context){
        List<JXNode> res = new LinkedList<JXNode>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(JXNode.t(e.outerHtml()));
            }
        }
        return res;
    }

    /**
     * 获取全部节点
     * @param context
     * @return
     */
    public List<JXNode> node(Elements context){
        return html(context);
    }

    /**
     * 抽取节点自有文本中全部数字
     * @param context
     * @return
     */
    public List<JXNode> num(Elements context){
        List<JXNode> res = new LinkedList<JXNode>();
        if (context!=null){
            Pattern pattern = Pattern.compile("\\d+");
            for (Element e:context){
                Matcher matcher = pattern.matcher(e.ownText());
                if (matcher.find()){
                    res.add(JXNode.t(matcher.group()));
                }
            }
        }
        return res;
    }

    /**
     * =====================
     * 下面是用于过滤器的函数
     */

    /**
     * 获取元素自己的子文本
     * @param e
     * @return
     */
    public String text(Element e){
        return e.ownText();
    }

    /**
     * 获取元素下面的全部文本
     * @param e
     * @return
     */
    public String allText(Element e){
        return e.text();
    }

    /**
     * 判断一个元素是不是最后一个同名同胞中的
     * @param e
     * @return
     */
    public boolean last(Element e){
        return CommonUtil.getElIndexInSameTags(e)==CommonUtil.sameTagElNums(e);
    }
    /**
     * 判断一个元素是不是同名同胞中的第一个
     * @param e
     * @return
     */
    public boolean first(Element e){
        return CommonUtil.getElIndexInSameTags(e)==1;
    }

    /**
     * 返回一个元素在同名兄弟节点中的位置
     * @param e
     * @return
     */
    public int position(Element e){
        return CommonUtil.getElIndexInSameTags(e);
    }

    /**
     * 判断是否包含
     * @param left
     * @param right
     * @return
     */
    public boolean contains(String left,String right){
       return left.contains(right);
    }

}
