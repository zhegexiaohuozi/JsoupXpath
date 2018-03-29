package cn.wanghaomiao.xpath.util;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Reflection;
import org.apache.commons.lang3.exception.ExceptionUtils;
import cn.wanghaomiao.xpath.core.AxisSelector;
import cn.wanghaomiao.xpath.core.Function;
import cn.wanghaomiao.xpath.core.NodeTest;
import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/2/28.
 */
public class Scanner {
    private static Map<String,AxisSelector> axisSelectorMap = new HashMap<>();
    private static Map<String,NodeTest> nodeTestMap = new HashMap<>();
    private static Map<String,Function> functionMap = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(Scanner.class);

    static {
        try {
            ClassPath axisPkg = ClassPath.from(Scanner.class.getClassLoader());
            String axisPkgPath = "cn.wanghaomiao.xpath.core.axis";
            String nodePkgPath = "cn.wanghaomiao.xpath.core.node";
            String functionPkgPath = "cn.wanghaomiao.xpath.core.function";
            for (ClassPath.ClassInfo classInfo : axisPkg.getTopLevelClasses(axisPkgPath)) {
                Class selectorClass = classInfo.load();
                Reflection.initialize(selectorClass);
                if (AxisSelector.class.isAssignableFrom(selectorClass)){
                    AxisSelector selector = (AxisSelector) selectorClass.newInstance();
                    axisSelectorMap.put(selector.name(),selector);
                }
            }
            for (ClassPath.ClassInfo classInfo : axisPkg.getTopLevelClasses(nodePkgPath)) {
                Class nodeTestClass = classInfo.load();
                Reflection.initialize(nodeTestClass);
                if (NodeTest.class.isAssignableFrom(nodeTestClass)){
                    NodeTest nodeTest = (NodeTest) nodeTestClass.newInstance();
                    nodeTestMap.put(nodeTest.name(), nodeTest);
                }
            }
            for (ClassPath.ClassInfo classInfo : axisPkg.getTopLevelClasses(functionPkgPath)) {
                Class funcClass = classInfo.load();
                Reflection.initialize(funcClass);
                if (Function.class.isAssignableFrom(funcClass)){
                    Function function = (Function) funcClass.newInstance();
                    functionMap.put(function.name(), function);
                }
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    public static AxisSelector findSelectorByName(String selectorName){
        AxisSelector selector = axisSelectorMap.get(selectorName);
        if (selector  == null){
            throw new NoSuchAxisException("not support axis: "+selectorName);
        }
        return selector;
    }

    public static NodeTest findNodeTestByName(String nodeTestName){
        NodeTest nodeTest = nodeTestMap.get(nodeTestName);
        if (nodeTest == null){
            throw new NoSuchFunctionException("not support nodeTest: "+nodeTestName);
        }
        return nodeTest;
    }

    public static Function findFunctionByName(String funcName){
        Function function = functionMap.get(funcName);
        if (function == null){
            throw new NoSuchFunctionException("not support function: "+funcName);
        }
        return function;
    }

}
