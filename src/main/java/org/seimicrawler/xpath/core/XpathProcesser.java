package org.seimicrawler.xpath.core;

import org.jsoup.select.Elements;
import org.seimicrawler.xpath.antlr.XpathBaseVisitor;
import org.seimicrawler.xpath.antlr.XpathParser;
import org.seimicrawler.xpath.model.JXNode;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2017/8/30.
 */
public class XpathProcesser extends XpathBaseVisitor<JXNode> {

    private boolean isRecursion = false;
    private Elements rootContext;

    public XpathProcesser(Elements root){
        rootContext = root;
    }



    @Override
    public JXNode visitAbsoluteLocationPathNoroot(XpathParser.AbsoluteLocationPathNorootContext ctx) {
        if (ctx.op.getType() == XpathParser.ABRPATH){
            // '//'
            isRecursion = true;
        }else {
            isRecursion = false;
        }
        return super.visitAbsoluteLocationPathNoroot(ctx);
    }

    @Override
    public JXNode visitRelativeLocationPath(XpathParser.RelativeLocationPathContext ctx) {

        return super.visitRelativeLocationPath(ctx);
    }
}
