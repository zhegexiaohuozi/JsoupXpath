package org.seimicrawler.xpath.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.seimicrawler.xpath.core.AxisSelector;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.NodeTest;
import org.seimicrawler.xpath.core.axis.AncestorOrSelfSelector;
import org.seimicrawler.xpath.core.axis.AncestorSelector;
import org.seimicrawler.xpath.core.axis.AttributeSelector;
import org.seimicrawler.xpath.core.axis.ChildSelector;
import org.seimicrawler.xpath.core.axis.DescendantOrSelfSelector;
import org.seimicrawler.xpath.core.axis.DescendantSelector;
import org.seimicrawler.xpath.core.axis.FollowingSelector;
import org.seimicrawler.xpath.core.axis.FollowingSiblingOneSelector;
import org.seimicrawler.xpath.core.axis.FollowingSiblingSelector;
import org.seimicrawler.xpath.core.axis.ParentSelector;
import org.seimicrawler.xpath.core.axis.PrecedingSelector;
import org.seimicrawler.xpath.core.axis.PrecedingSiblingOneSelector;
import org.seimicrawler.xpath.core.axis.PrecedingSiblingSelector;
import org.seimicrawler.xpath.core.axis.SelfSelector;
import org.seimicrawler.xpath.core.function.Concat;
import org.seimicrawler.xpath.core.function.Contains;
import org.seimicrawler.xpath.core.function.Count;
import org.seimicrawler.xpath.core.function.First;
import org.seimicrawler.xpath.core.function.Last;
import org.seimicrawler.xpath.core.function.Not;
import org.seimicrawler.xpath.core.function.Position;
import org.seimicrawler.xpath.core.function.StartsWith;
import org.seimicrawler.xpath.core.function.StringLength;
import org.seimicrawler.xpath.core.function.SubString;
import org.seimicrawler.xpath.core.function.SubStringAfter;
import org.seimicrawler.xpath.core.function.SubStringBefore;
import org.seimicrawler.xpath.core.function.SubStringEx;
import org.seimicrawler.xpath.core.node.AllText;
import org.seimicrawler.xpath.core.node.Html;
import org.seimicrawler.xpath.core.node.Node;
import org.seimicrawler.xpath.core.node.Num;
import org.seimicrawler.xpath.core.node.OuterHtml;
import org.seimicrawler.xpath.core.node.Text;
import org.seimicrawler.xpath.exception.NoSuchAxisException;
import org.seimicrawler.xpath.exception.NoSuchFunctionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 考虑更广泛的兼容性，替换掉 FastClasspathScanner，采用手工注册
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2018/2/28.
 */
public class Scanner {
    private static Map<String, AxisSelector> axisSelectorMap = new HashMap<>();
    private static Map<String, NodeTest> nodeTestMap = new HashMap<>();
    private static Map<String, Function> functionMap = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(Scanner.class);

    static {
//        new FastClasspathScanner(Function.class.getPackage().getName())
////                .verbose()
//                .matchClassesImplementing(Function.class, new ImplementingClassMatchProcessor<Function>() {
//                    @Override
//                    public void processMatch(Class<? extends Function> funcClass) {
//                        Function function;
//                        try {
//                            function = funcClass.newInstance();
//                            functionMap.put(function.name(), function);
//                        } catch (Exception e) {
//                            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
//                        }
//                    }
//                })
//                .matchClassesImplementing(NodeTest.class, new ImplementingClassMatchProcessor<NodeTest>() {
//                    @Override
//                    public void processMatch(Class<? extends NodeTest> nodeTestClass) {
//                        NodeTest nodeTest;
//                        try {
//                            nodeTest = nodeTestClass.newInstance();
//                            nodeTestMap.put(nodeTest.name(), nodeTest);
//                        }  catch (Exception e) {
//                            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
//                        }
//
//                    }
//                })
//                .matchClassesImplementing(AxisSelector.class, new ImplementingClassMatchProcessor<AxisSelector>() {
//                    @Override
//                    public void processMatch(Class<? extends AxisSelector> axisSelectorClass) {
//                        AxisSelector axisSelector;
//                        try {
//                            axisSelector = axisSelectorClass.newInstance();
//                            axisSelectorMap.put(axisSelector.name(), axisSelector);
//                        }  catch (Exception e) {
//                            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
//                        }
//                    }
//                })
//                .scan();
        //axis
        registerAxisSelector(AncestorOrSelfSelector.class);
        registerAxisSelector(AncestorSelector.class);
        registerAxisSelector(AttributeSelector.class);
        registerAxisSelector(ChildSelector.class);
        registerAxisSelector(DescendantOrSelfSelector.class);
        registerAxisSelector(DescendantSelector.class);
        registerAxisSelector(FollowingSelector.class);
        registerAxisSelector(FollowingSiblingOneSelector.class);
        registerAxisSelector(FollowingSiblingSelector.class);
        registerAxisSelector(ParentSelector.class);
        registerAxisSelector(PrecedingSelector.class);
        registerAxisSelector(PrecedingSiblingOneSelector.class);
        registerAxisSelector(PrecedingSiblingSelector.class);
        registerAxisSelector(SelfSelector.class);
        //func
        registerFunction(Concat.class);
        registerFunction(Contains.class);
        registerFunction(Count.class);
        registerFunction(First.class);
        registerFunction(Last.class);
        registerFunction(Not.class);
        registerFunction(Position.class);
        registerFunction(StartsWith.class);
        registerFunction(StringLength.class);
        registerFunction(SubString.class);
        registerFunction(SubStringAfter.class);
        registerFunction(SubStringBefore.class);
        registerFunction(SubStringEx.class);
        //nodeTest
        registerNodeTest(AllText.class);
        registerNodeTest(Html.class);
        registerNodeTest(Node.class);
        registerNodeTest(Num.class);
        registerNodeTest(OuterHtml.class);
        registerNodeTest(Text.class);
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

    public static void registerFunction(Class<? extends Function> func){
        Function function;
        try {
            function = func.newInstance();
            functionMap.put(function.name(), function);
        } catch (Exception e) {
            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
        }
    }

    public static void registerNodeTest(Class<? extends NodeTest> nodeTestClass){
        NodeTest nodeTest;
        try {
            nodeTest = nodeTestClass.newInstance();
            nodeTestMap.put(nodeTest.name(), nodeTest);
        }  catch (Exception e) {
            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
        }
    }

    public static void registerAxisSelector(Class<? extends AxisSelector> axisSelectorClass){
        AxisSelector axisSelector;
        try {
            axisSelector = axisSelectorClass.newInstance();
            axisSelectorMap.put(axisSelector.name(), axisSelector);
        }  catch (Exception e) {
            logger.info(ExceptionUtils.getRootCauseMessage(e),e);
        }
    }

}
