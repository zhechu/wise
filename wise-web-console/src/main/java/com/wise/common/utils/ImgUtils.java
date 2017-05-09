package com.wise.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * 图片处理工具类
 * @author lingyuwang
 *
 */
public class ImgUtils {

	private static Logger logger = Logger.getLogger(ImgUtils.class);
	
	private ImgUtils() {}
	
	/**
	 * 图片 base64 data 格式数据转换为图片文件
	 * @param base64Data 图片 base64 data 格式数据
	 * @param filePath 保存的图片文件路径
	 * @return
	 */
	public static File base64DataToImg(String base64Data, String filePath) {
		OutputStream out = null;
		File file = null;
		try {
			Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
			Method mainMethod = clazz.getMethod("decode", String.class);  
			mainMethod.setAccessible(true);  
			byte[] b = (byte[]) mainMethod.invoke(null, base64Data);
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			file = new File(filePath);
			boolean isCreateSuccess = FileUtils.createFile(file);
			if (!isCreateSuccess) {
				throw new RuntimeException("创建文件失败");
			}
			out = new FileOutputStream(file);
			out.write(b);
			out.flush();
			return file;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
		}
		return file;
	}
	
	/**
	 * 图片文件转换为base64 data 格式数据
	 * @param filePath 图片路径
	 * @return
	 */
	public static String imgToBase64Data(String filePath) {
	    InputStream in = null;
	    String base64Data = null;
	    try {
	        in = new FileInputStream(filePath);
	        byte[] data = new byte[in.available()];
	        in.read(data);
	        in.close();
	        
	        Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
	        Method mainMethod = clazz.getMethod("encode", byte[].class);  
	        mainMethod.setAccessible(true);  
	        base64Data = (String) mainMethod.invoke(null, new Object[] {data});
	    } catch (Exception e) {
	    	logger.error(e.getMessage(), e);
	    } finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	    return base64Data;
	}
	
	public static void main(String[] args) {
		String strImg = ImgUtils.imgToBase64Data("d:/profile_small.jpg");
	    System.out.println(strImg);
	    ImgUtils.base64DataToImg(strImg, "d:/abc/profile_small2.png");
	}
	
}
