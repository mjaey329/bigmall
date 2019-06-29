package com.dingmj.bigmall.core.util;

import java.util.regex.Pattern;

/**
 * <h1>正则表达式 工具类</h1>
 * @author DMJ
 * @date 2019-06-29 2:47
 */
public class RegexUtil {

    /**
     * <h2>用户名正则表达式</h2>
     * <p>"a-z" ,"A-Z" ,"0-9","_","中文"</p>
     * <p>结尾不能为 "_"</p>
     * <p>长度在 6 - 20</p>
     */
    public static final String REGEX_USERNAME="^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";


    public static boolean isMatch(final String regex,final CharSequence input){
        return input != null && input.length() > 0 && Pattern.matches(regex,input);
    }


    /**
     * <h2>匹配用户名</h2>
     * @param input
     * @return {@code true}:yes <br>{@code false}:no
     */
    public static boolean isUsername(final CharSequence input){
        return isMatch(REGEX_USERNAME,input);
    }
}
