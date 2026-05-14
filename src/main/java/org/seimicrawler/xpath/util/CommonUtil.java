package org.seimicrawler.xpath.util;
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

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.seimicrawler.xpath.core.Constants;
import org.seimicrawler.xpath.core.Scope;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * Date: 14-3-15
 */
public class CommonUtil {

    /**
     * 获取同名元素在同胞中的index
     * @param e 目标元素
     * @param scope 上下文
     * @return 同名元素的位置索引（从1开始）
     */
    public static int getElIndexInSameTags(Element e,Scope scope){
        return getElIndexInSameTags(e, null, scope);
    }

    /**
     * 获取同名元素在同胞中的index（使用预构建的 HashSet 加速 contains 查找）
     * @param e 目标元素
     * @param contextSet 预构建的上下文元素集合，传 null 则回退到 scope.context().contains()
     * @param scope 上下文
     * @return 同名元素的位置索引（从1开始）
     */
    public static int getElIndexInSameTags(Element e, Set<Element> contextSet, Scope scope){
        Elements chs = e.parent().children();
        int index = 1;
        for (Element cur : chs) {
            if (e.tagName().equals(cur.tagName()) && (contextSet != null ? contextSet.contains(cur) : scope.context().contains(cur))) {
                if (e.equals(cur)) {
                    break;
                } else {
                    index += 1;
                }
            }
        }
        return index;
    }


    /**
     * 获取同胞中同名元素的数量
     * Jsoup文档模型中，空白行和元素均属于同胞也有自己独立的siblingIndex，这对于xpath语法统计，空白行等是没有任何意义的，不应该计入siblingIndex。所以需要自行独立统计，不能直接使用siblingIndex。
     * @param e 目标元素
     * @param scope 上下文
     * @return 同名元素总数
     */
    public static int sameTagElNums(Element e,Scope scope){
        return sameTagElNums(e, null, scope);
    }

    /**
     * 获取同胞中同名元素的数量（使用预构建的 HashSet 加速 contains 查找）
     * @param e 目标元素
     * @param contextSet 预构建的上下文元素集合，传 null 则回退到 scope.context().contains()
     * @param scope 上下文
     * @return 同名元素总数
     */
    public static int sameTagElNums(Element e, Set<Element> contextSet, Scope scope){
        int count = 0;
        Elements els = e.parent().getElementsByTag(e.tagName());
        for (Element el:els){
            if (contextSet != null ? contextSet.contains(el) : scope.context().contains(el)){
                count++;
            }
        }
        return count;
    }

    public static int getIndexInContext(Scope scope,Element el){
        for (int i = 0;i<scope.context().size();i++){
            Element tmp = scope.context().get(i);
            if (Objects.equals(tmp,el)){
                return i+1;
            }
        }
        return Integer.MIN_VALUE;
    }

     public static Elements followingSibling(Element el){
        Elements rs = new Elements();
        Node tmp = el.nextSibling();
        while (tmp!=null){
         if (tmp instanceof Element ){
             rs.add((Element) tmp);
         } else if (tmp instanceof TextNode) {
             Element txt = new Element("text");
             txt.text(((TextNode) tmp).text());
             rs.add(txt);
         }
         tmp = tmp.nextSibling();
        }
        if (rs.size() > 0){
            return rs;
        }
        return null;
    }

    public static Elements precedingSibling(Element el){
        Elements rs = new Elements();
        Node tmp = el.previousSibling();
        while (tmp!=null){
            if (tmp instanceof Element ){
                rs.add((Element) tmp);
            } else if (tmp instanceof TextNode) {
                Element txt = new Element("text");
                txt.text(((TextNode) tmp).text());
                rs.add(txt);
            }
            tmp = tmp.previousSibling();
        }

        if (rs.size() > 0){
            return rs;
        }
        return null;
    }

    public static void setSameTagIndexInSiblings(Element ori,int index){
        if (ori == null){
            return;
        }
        ori.attr(Constants.EL_SAME_TAG_INDEX_KEY,String.valueOf(index));
    }

    public static int getJxSameTagIndexInSiblings(Element ori){
        String val = ori.attr(Constants.EL_SAME_TAG_INDEX_KEY);
        if (StringUtils.isBlank(val)){
            return -1;
        }
        return Integer.parseInt(val);
    }

    public static void setSameTagNumsInSiblings(Element ori,int nums){
        if (ori == null){
            return;
        }
        ori.attr(Constants.EL_SAME_TAG_ALL_NUM_KEY,String.valueOf(nums));
    }

    public static int getJxSameTagNumsInSiblings(Element ori){
        String val = ori.attr(Constants.EL_SAME_TAG_ALL_NUM_KEY);
        if (StringUtils.isBlank(val)){
            return -1;
        }
        return Integer.parseInt(val);
    }

    /**
     * 批量预计算结果容器，包含每个元素的同名索引和同名总数。
     * 一次性遍历所有涉及到的父节点的子元素，避免对每个元素重复遍历父节点子元素列表。
     * 将原本 O(N × M) 的复杂度降为 O(P × M)，其中 P 为不同父节点数，M 为最大子节点数。
     */
    public static class PredicateIndexInfo {
        /** 元素 -&gt; 同名元素在 context 中的顺序索引（从1开始） */
        public final Map<Element, Integer> indexMap;
        /** 元素 -&gt; 其父节点下同名且在 context 中的元素总数 */
        public final Map<Element, Integer> countMap;

        public PredicateIndexInfo(Map<Element, Integer> indexMap, Map<Element, Integer> countMap) {
            this.indexMap = indexMap;
            this.countMap = countMap;
        }
    }

    /**
     * 批量预计算 context 中所有元素的同名索引和同名总数。
     * 按父节点分组，每个父节点的子元素列表只遍历两次（计数+编号），
     * 相比逐个元素调用 getElIndexInSameTags / sameTagElNums 性能大幅提升。
     *
     * @param context    当前上下文中的元素列表
     * @param contextSet 预构建的上下文 HashSet（加速 contains 判断）
     * @return 预计算结果
     */
    public static PredicateIndexInfo preComputePredicateIndices(Elements context, Set<Element> contextSet) {
        Map<Element, Integer> indexMap = new HashMap<>();
        Map<Element, Integer> countMap = new HashMap<>();

        // 收集所有涉及的父节点（保持顺序以便调试）
        Set<Element> parents = new LinkedHashSet<>();
        for (Element e : context) {
            if (e.parent() != null) {
                parents.add(e.parent());
            }
        }

        for (Element parent : parents) {
            // 第一遍：统计每个 tagName 在 context 中的总数
            Map<String, Integer> tagTotalMap = new HashMap<>();
            for (Element child : parent.children()) {
                if (contextSet.contains(child)) {
                    String tag = child.tagName();
                    tagTotalMap.merge(tag, 1, Integer::sum);
                }
            }

            // 第二遍：为每个 context 中的子元素分配同名索引
            Map<String, Integer> tagCounterMap = new HashMap<>();
            for (Element child : parent.children()) {
                if (contextSet.contains(child)) {
                    String tag = child.tagName();
                    int idx = tagCounterMap.getOrDefault(tag, 0) + 1;
                    tagCounterMap.put(tag, idx);
                    indexMap.put(child, idx);
                    countMap.put(child, tagTotalMap.get(tag));
                }
            }
        }

        return new PredicateIndexInfo(indexMap, countMap);
    }
}
