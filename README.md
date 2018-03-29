JsoupXpath
==========

A html parser with xpath base on Jsoup.Maybe it is the best in java,Just try it.

I will write more document later...

## 简介 ##

**JsoupXpath** 是一款纯Java开发的使用xpath解析html的解析器，xpath的Lexer和Parser基于Antlr4构建，html的DOM树生成采用Jsoup，故命名为JsoupXpath.
为了在java里也享受xpath的强大与方便但又苦于找不到一款足够好用的xpath解析器，故开发了JsoupXpath。JsoupXpath的实现逻辑清晰，扩展方便，
支持完备的W3C XPATH 1.0标准语法，W3C规范：http://www.w3.org/TR/1999/REC-xpath-19991116。

## 20180330快讯 ##

JsoupXpath已经基本完成了基于Antlr4的重构，从而支持完备的W3C XPATH 1.0标准语法，为大家带来更加爽快的体验！

### 当前开发测试版本 ###

```
<dependency>
   <groupId>cn.wanghaomiao</groupId>
   <artifactId>JsoupXpath</artifactId>
   <version>2.0-SNAPSHOT</version>
</dependency>
```


# Change Log #

https://github.com/zhegexiaohuozi/JsoupXpath/releases

# 社区讨论 #
大家有什么问题或建议现在都可以选择通过下面的邮件列表讨论，首次发言前需先订阅并等待审核通过（主要用来屏蔽广告宣传等）
- 订阅:请发邮件到 `seimicrawler+subscribe@googlegroups.com`
- 发言:请发邮件到 `seimicrawler@googlegroups.com`
- 退订:请发邮件至 `seimicrawler+unsubscribe@googlegroups.com`

## 快速开始 ##

如果不方便使用maven，可以直接使用lib下的依赖包跑起来试试，如方便可直接使用如下dependency(中央maven库,最新版本0.3.2)：
```
<dependency>
   <groupId>cn.wanghaomiao</groupId>
   <artifactId>JsoupXpath</artifactId>
   <version>2.0-SNAPSHOT</version>
</dependency>
```

依赖配置好后，就可以使用如下例子进行体验了！

```
String xpath="//div[@id='post_list']/div[./div/div/span[@class='article_view']/a/num()>1000]/div/h3/allText()";
String doc = "...";
JXDocument jxDocument = new JXDocument(doc);
List<Object> rs = jxDocument.sel(xpath);
for (Object o:rs){
	if (o instanceof Element){
		int index = ((Element) o).siblingIndex();
		System.out.println(index);
	}
	System.out.println(o.toString());
}
```
其他可以参考 `cn.wanghaomiao.example`包下的例子

## 语法 ##

支持完备的W3C XPATH 1.0标准语法，W3C规范：http://www.w3.org/TR/1999/REC-xpath-19991116

### 关于使用Xpath的一些注意事项 ####

非常不建议直接粘贴Firefox或chrome里生成的Xpath，这些浏览器在渲染页面会根据标准自动补全一些标签，如table标签会自动加上tbody标签，这样生成的Xpath路径显然不是最通用的，所以很可能就取不到值。所以，要使用Xpath并感受Xpath的强大以及他所带来便捷与优雅最好就是学习下Xpath的标准语法，这样应对各种问题才能游刃有余，享受Xpath的真正威力！

## 函数 ##

- `text()` 提取节点的自有文本
- `node()` 提取所有节点
- `position()` 返回当前节点所处在同胞中的位置
- `last()` 返回同级节点中的最后那个节点
- `first()` 返回同级节点中的第一个节点

### NodeTest扩展 ###
- `allText()`提取节点下全部文本，取代类似 `//div/h3//text()`这种递归取文本用法
- `html()`获取全部节点的内部的html
- `outerHtml()`获取全部节点的 包含节点本身在内的全部html
- `num()`抽取节点自有文本中全部数字，如果知道节点的自有文本(即非子代节点所包含的文本)中只存在一个数字，如阅读数，评论数，价格等那么直接可以直接提取此数字出来。如果有多个数字将提取第一个匹配的连续数字。

## 轴 ##
- `self` 节点自身
- `parent` 父节点
- `child` 直接子节点
- `ancestor` 全部祖先节点 父亲，爷爷 ， 爷爷的父亲...
- `ancestor-or-self`全部祖先节点和自身节点
- `descendant` 全部子代节点 儿子，孙子，孙子的儿子...
- `descendant-or-self` 全部子代节点和自身
- `preceding-sibling` 节点前面的全部同胞节点
- `following-sibling` 节点后面的全部同胞节点

### 轴实用扩展 ###
- `preceding-sibling-one` 前一个同胞节点（扩展）
- `following-sibling-one` 返回下一个同胞节点(扩展) 语法 
- `sibling` 全部同胞（扩展）

## 操作符 ##

- `a+b` 加 返回数值结果
- `a-b` 减 返回数值结果
- `a=b` 判断是否相等返回Boolean
- `a!=b` 不等于 返回Boolean
- `a>b` 大于 返回Boolean
- `a>=b` 大于等于 返回Boolean
- `a<b` 小于 返回Boolean
- `a<=b` 小于等于 返回Boolean 

### 操作符扩展 ###
- `a^=b` 字符串a以字符串b开头 a startwith b
- `a*=b` a包含b, a contains b
- `a$=b` a以b结尾 a endwith b
- `a~=b` a的内容符合 正则表达式b
- `a!~b` a的内容不符合 正则表达式b


## 应用的项目 ##
目前JsoupXpath被大量使用的项目或是组织有：[SeimiCrawler](https://github.com/zhegexiaohuozi/SeimiCrawler)。
如果您也有项目在使用JsoupXpath，并希望出现在这个列表中，可以通过下面的联系方式联系我，邮件格式可以为：
```
项目或组织名称：XX
项目或组织URL：http://xxx.xxx.cc
```
