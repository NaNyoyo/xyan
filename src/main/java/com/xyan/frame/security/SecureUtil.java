package com.xyan.frame.security;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author wangming
 * 加密工具类
 */
public class SecureUtil {

	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }
	
	
	
	
	/**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }
    
    /**
     * @Author:wangming
     * @Description:base64加密
     * @param str
     * @return
     * @since 2016年11月1日下午3:04:54
     */
    public static String base64Encode(String str){
    	return Base64.encodeBase64String(str.getBytes());
    }
    /**
     * @Author:wangming
     * @Description:base64加密
     * @param str
     * @return
     * @since 2016年11月1日下午3:04:54
     */
    public static String base64Decode(String str){
    	return new String(Base64.decodeBase64(str));
    }
    
    public static void main(String[] args) {
    	String str="你好。。。";
    	System.out.println(base64Encode(str));
    	System.out.println(base64Decode(base64Encode(str)));
	}
}
