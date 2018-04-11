package org.seimicrawler.xpath.util;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.ImplementingClassMatchProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.NodeTest;
import org.seimicrawler.xpath.exception.NoSuchAxisException;
import org.seimicrawler.xpath.exception.NoSuchFunctionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/2/28.
 */
public class Scanner {
    private static Map<String, AxisSelector> axisSelectorMap = new HashMap<>();
    private static Map<String, NodeTest> nodeTestMap = new HashMap<>();
    private static Map<String, Function> functionMap = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(Scanner.class);

    static {
        new FastClasspathScanner(Function.class.getPackage().getName())
//                .verbose()
                .matchClassesImplementing(Function.class, new ImplementingClassMatchProcessor<Function>() {
                    @Override
                    public void processMatch(Class<? extends Function> funcClass) {
                        Function function;
                        try {
                            function = funcClass.newInstance();
                            functionMap.put(function.name(), function);
                        } catch (Exception e) {
                            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
                        }
                    }
                })
                .matchClassesImplementing(NodeTest.class, new ImplementingClassMatchProcessor<NodeTest>() {
                    @Override
                    public void processMatch(Class<? extends NodeTest> nodeTestClass) {
                        NodeTest nodeTest;
                        try {
                            nodeTest = nodeTestClass.newInstance();
                            nodeTestMap.put(nodeTest.name(), nodeTest);
                        }  catch (Exception e) {
                            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
                        }

                    }
                })
                .matchClassesImplementing(AxisSelector.class, new ImplementingClassMatchProcessor<AxisSelector>() {
                    @Override
                    public void processMatch(Class<? extends AxisSelector> axisSelectorClass) {
                        AxisSelector axisSelector;
                        try {
                            axisSelector = axisSelectorClass.newInstance();
                            axisSelectorMap.put(axisSelector.name(), axisSelector);
                        }  catch (Exception e) {
                            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
                        }
                    }
                })
                .scan();
    }

    public static AxisSelector findSelectorByName(String selectorName) {
        AxisSelector selector = axisSelectorMap.get(selectorName);
        if (selector == null) {
            throw new NoSuchAxisException("not support axis: " + selectorName);
        }
        return selector;
    }

    public static NodeTest findNodeTestByName(String nodeTestName) {
        NodeTest nodeTest = nodeTestMap.get(nodeTestName);
        if (nodeTest == null) {
            throw new NoSuchFunctionException("not support nodeTest: " + nodeTestName);
        }
        return nodeTest;
    }

    public static Function findFunctionByName(String funcName) {
        Function function = functionMap.get(funcName);
        if (function == null) {
            throw new NoSuchFunctionException("not support function: " + funcName);
        }
        return function;
    }

}
