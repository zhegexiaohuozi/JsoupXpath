package org.seimicrawler.xpath.core.function;

import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @Author: eternity
 * @CreateTime: 2023-11-26  11:38
 * @Description: 正则匹配，是否符合要求
 * 例如 从a标签取数据 只匹配 /book/数字/   表达式：//a[matches(@href, '^/book/\d+/$')]/@href
 * @Version: 1.0
 */
public class Matches implements Function {

    private final Map<String, Pattern> pools = new HashMap<>();

    @Override
    public String name() {
        return "matches";
    }

    @Override
    public XValue call(Scope scope, List<XValue> params) {
        String first = params.get(0).asString();
        String second = params.get(1).asString();
        Pattern pattern = getPattern(second);
        return XValue.create(pattern.matcher(first).find());
    }

    private Pattern getPattern(String regex) {
        Pattern pattern = pools.get(regex);
        if (Objects.isNull(pattern)) {
            synchronized (pools) {
                pattern = Optional.of(pools.get(regex)).orElseGet(() -> {
                    Pattern compile = Pattern.compile(regex);
                    pools.put(regex, compile);
                    return compile;
                });
            }
        }
        return pattern;
    }
}
