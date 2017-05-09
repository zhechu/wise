package com.wise.core.service;

import java.io.File;

public interface UploadService {
	
	/**
	 * 上传单个文件
	 * @param file 文件字节
	 * @param name 文件名
	 * @param size 文件大小
	 * @return
	 * @throws Exception
	 */
	public String uploadPic(byte[] file, String name, Long size);
	
	/**
	 * 上传单个文件
	 * @param file 文件对象
	 * @param name 文件名
	 * @return
	 */
	public String uploadPic(File file, String name);

}
