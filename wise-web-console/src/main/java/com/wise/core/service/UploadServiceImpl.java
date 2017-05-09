package com.wise.core.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.wise.common.fdfs.FastDFSUtils;

/**
 * 上传文件
 * @author lingyuwang
 *
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService{

	@Override
	public String uploadPic(byte[] file, String name, Long size) {
		return FastDFSUtils.uploadFile(file, name, size);
	}

	@Override
	public String uploadPic(File file, String name) {
		return FastDFSUtils.uploadFile(file, name);
	}
	
}
