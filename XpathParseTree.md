这里是JsoupXpath的基于Antlr4的语法解析树示例，方便大家更好的一看JsoupXpath的语法处理能力与语法执行过程
- `//ul[@class='subject-list']/li[./div/div/span[@class='pl']/num()>(1000+90*(2*50))][last()][1]/div/h2/allText()`
这个主要是一些表达式嵌套的解析示例，点击图片可以查看原图
[![muti_expr](https://imgs.wanghaomiao.cn/jsoupxpath/antlr4_parse_tree_muti_expr.png)](https://imgs.wanghaomiao.cn/jsoupxpath/antlr4_parse_tree_muti_expr.png)

- `//ul[@class='subject-list']/li[not(contains(self::li/div/div/span[@class='pl']//text(),'14582'))]/div/h2//text()`
这个是对内置函数支持的一个解析示例，点击图片可以查看原图
[![functions](https://imgs.wanghaomiao.cn/jsoupxpath/antlr4_parse_tree_functions_v2.png)](https://imgs.wanghaomiao.cn/jsoupxpath/antlr4_parse_tree_functions_v2.png)
