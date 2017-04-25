package com.wise.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * 密码管理工具
 * @author lingyuwang
 *
 */
public final class SecureUtil {

	private SecureUtil() {}

	/**
	 * 加密
	 * @param pwd 密码
	 * @param salt 盐值
	 * @return 加密后的字符串
	 */
	public static String encryptByMd5(String pwd, String salt){
		if (StringUtils.isBlank(pwd) || StringUtils.isBlank(salt)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		MessageDigest md5 = null;
		try {
		    md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) { }
		char[] charArray = new StringBuffer().append(pwd).append(salt).toString().trim().toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++){
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		for (int i = 0; i < md5Bytes.length; i++) {
		    int val = ((int) md5Bytes[i]) & 0xff;
		    if (val < 16){
		    	sb.append("0");
		    }
		    sb.append(Integer.toHexString(val));
		}
        return sb.toString();
	}
	
	/**
	 * 生成盐值
	 * @return
	 */
	public static String generateSalt(){
		return generateSalt(Calendar.getInstance().getTime());
	}
	
	/**
	 * 生成盐值
	 * @param date
	 * @return
	 */
	public static String generateSalt(Date date){
		return new StringBuffer()
			.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(null != date ? date : Calendar.getInstance().getTime()))
			.append(",").append(new Random().nextInt(Integer.MAX_VALUE)).toString();
	}

	/**
	 * base64 加密
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static String encodeByBase64(String code) throws UnsupportedEncodingException, Exception {  
		Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod = clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
        Object retObj = mainMethod.invoke(null, new Object[] { code.getBytes() });  
        return (String) retObj;
	}  

	/**
	 * base64 解密
	 * @param code
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public static String decodeByBase64(String code) throws UnsupportedEncodingException, Exception {  
		Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
		Method mainMethod = clazz.getMethod("decode", String.class);  
		mainMethod.setAccessible(true);  
		Object retObj = mainMethod.invoke(null, code);  
		return new String((byte[]) retObj);  
	}  

	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		String salt = SecureUtil.generateSalt();
		String pwd = SecureUtil.encryptByMd5("123456", salt);
		System.out.println(salt);
		System.out.println("--------------------------------------");
		System.out.println(pwd);
		
		/*String str = "abadjfajerjaqwrasdf";  
		String code = encodeByBase64(str);  
		System.err.println("加密前：" + str);  
		System.err.println("加密后：" + code);  
		System.err.println("解密后：" + decodeByBase64(code));*/  
	}
	
}
