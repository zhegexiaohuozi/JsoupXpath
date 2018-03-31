package cn.wanghaomiao.xpath.core.node;

import cn.wanghaomiao.xpath.BaseTest;
import cn.wanghaomiao.xpath.core.Scope;
import cn.wanghaomiao.xpath.core.XValue;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

/**
 * Num Tester.
 *
 * @author seimimaster@gmail.com
 * @version 1.0
 */
public class NumTest extends BaseTest {

    /**
     * Method: call(Elements context)
     */
    @Test
    public void testCall() throws Exception {
        Elements context = new Elements();
        Element el = new Element("V");
        el.appendText("test 33.69");
        context.add(el);
        Num n = new Num();
        XValue v = n.call(Scope.create(context));
        logger.info("v = {}",v);
        Assert.assertEquals(33.69,v.asDouble(),0.00000000000001);
    }

    @Test
    public void testShort() throws Exception {
        Elements context = new Elements();
        Element el = new Element("V");
        el.appendText("test .69");
        context.add(el);
        Num n = new Num();
        XValue v = n.call(Scope.create(context));
        logger.info("v = {}",v);
        Assert.assertEquals(0.69,v.asDouble(),0.00000000000001);
    }

    @Test
    public void testOnZero() throws Exception {
        Elements context = new Elements();
        Element el = new Element("V");
        el.appendText("test 69.");
        context.add(el);
        Num n = new Num();
        XValue v = n.call(Scope.create(context));
        logger.info("v = {}",v);
        Assert.assertEquals(69,v.asDouble(),0.00000000000001);
    }


} 
