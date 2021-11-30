package org.seimicrawler.xpath.core;

import java.util.regex.Pattern;

/**
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 * @since 2020/9/24.
 */
public class Constants {
    public final static String DEF_TEXT_TAG_NAME = "JX_TEXT";
    public final static String EL_SAME_TAG_INDEX_KEY = "EL_SAME_TAG_INDEX";
    public final static String EL_SAME_TAG_ALL_NUM_KEY = "EL_SAME_TAG_ALL_NUM";
    public final static String EL_DEPTH_KEY = "EL_DEPTH";

    public final static Pattern NUM_PATTERN = Pattern.compile("\\d*\\.?\\d+");
}
