package cn.wanghaomiao.xpath.model;

import cn.wanghaomiao.xpath.exception.NoSuchAxisException;
import cn.wanghaomiao.xpath.exception.NoSuchFunctionException;
import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * JXDocument Tester.
 *
 * @author <et.tw@163.com>
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class JXDocumentTest {

    private JXDocument underTest;

    @Before
    public void before() throws Exception {
        String html = "<html><body><script>console.log('aaaaa')</script><div class='test'>搜易贷致力于普惠金融，专注于互联网投资理财与小额贷款，搭建中国最大、用户体验最好的个人及中小企业的互联网信贷平台</div><div class='xiao'>Two</div></body></html>";
        underTest = new JXDocument(html);
    }
    /**
     * Method: sel(String xpath)
     */
    @Test
    public void testSel() throws Exception {
        String xpath="//script[1]/text()";
        List<Object> res = underTest.sel(xpath);
        Assert.assertNotNull(res);
        Assert.assertTrue(res.size()>0);
        System.out.println(StringUtils.join(res,","));
    }

    @Test
    public void testNotMatchFilter() throws Exception {
        String xpath="//div[@class!~'xiao']/text()";
        List<Object> res = underTest.sel(xpath);
        System.out.println(StringUtils.join(res,","));
    }
}
