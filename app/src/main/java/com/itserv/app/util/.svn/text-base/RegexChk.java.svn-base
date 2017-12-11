package com.itserv.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @project name：yyshed
 * @type name：RegexChk
 * @description：正则表达式
 * @author：gang
 * @date time：2017-6-17 上午9:56:19
 */
public class RegexChk {

    public static boolean startCheck(String reg, String string) {
        boolean tem;

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
//		tem = matcher.matches();
        tem = matcher.find();
        return tem;
    }

    /**
     * @description：由数字、字母或者下划线组成
     * @author：gang
     * @date time：2017-6-19 下午6:31:16
     */
    public static boolean checkUserName(String username) {
        String reg = "^/\\w+$";
        return startCheck(reg, username);
    }

    /**
     * @description：验证用户密码 长度 8-20位，必须包括字母、数字、特殊符号的组合
     * @author：gang
     * @date time：2017-6-17 上午10:04:59
     */
    public static boolean checkPassword(String psd) {
        String pattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(psd);
        System.out.println(m.matches());
        return m.matches();
    }

    /**
     * (?=.*?[\u4E00-\u9FA5])表示一定有汉字，也就说明不会全是数字或字母
     * [\dA-Za-z\u4E00-\u9FA5]+表示由汉字、数字、字母构成，越长越好
     *
     * @param psd
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-19 下午3:09:25
     */
    public static boolean checkAddress(String psd) {
        String reg = "^(?=.*?[u4E00-\u9FA5])[dA-Za-z\u4E00-\u9FA5]+$";
        return startCheck(reg, psd);
    }

    /**
     * 网关验证
     *
     * @param snCode
     * @return boolean
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-20 上午9:59:55
     */
    public static boolean checkGateCode(String snCode) {
        String reg = "/^[A-Z]{3}[0-9]{12}[A-Z0-9]{3}$/";
        return startCheck(reg, snCode);
    }

    /**
     * @param str
     * @return boolean
     * @prama: str 要判断是否包含特殊字符的目标字符串
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-24 下午7:26:06
     */

    public static boolean compileExChar(String str) {
        String reg = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        // Pattern pattern = Pattern.compile(reg);
        // Matcher m = pattern.matcher(str);
        // return m.find();
        return startCheck(reg, str);
    }
}
