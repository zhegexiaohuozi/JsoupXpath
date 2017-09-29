package cn.wanghaomiao.xpath.core;

import org.seimicrawler.xpath.core.XpathEvaluator;
import org.seimicrawler.xpath.model.Node;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

/**
 * XpathEvaluator Tester.
 *
 * @author <seimimaster@gmail.com>
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
public class XpathEvaluatorTest {
    private XpathEvaluator underTest;

    @Before
    public void before() throws Exception {
        underTest = new XpathEvaluator();
    }

    /**
     * Method: getXpathNodeTree(String xpath)
     */
    @Test
    public void testGetXpathNodeTree() throws Exception {
        List<Node> nodes = underTest.getXpathNodeTree("//meta[@charset]");
        Assert.assertTrue(StringUtils.isBlank(nodes.get(0).getPredicate().getLeft()));
    }

    @Test
    public void testXpathNodeTree(){
        List<Node> nodes = underTest.getXpathNodeTree("//meta[@charset!~'xx']/@href");
        System.out.println(nodes);
    }
}
