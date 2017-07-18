package com.huike.base.tools.upload;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public  class SpringFileUploadUtil {

	public  static boolean uploadFile(MultipartFile file, String upload_path, String fileName)
{

		// 拼接最终全路径
		String all_upload_path = new StringBuilder(upload_path).toString();

		try {
			File targetFileDir = new File(all_upload_path);

			// 目录不存在创建目录
			if (!targetFileDir.exists()) {
				// logger.info("首次创建目录:" + all_upload_path);
				targetFileDir.mkdirs();
			}

			File targetFile = new File(all_upload_path, fileName);
			// 保存
			file.transferTo(targetFile);

			return true;
		} catch (Exception e) {
			// logger.error("上传文件异常fileName:" + all_upload_path + fileName, e);
		}

		return false;
	}
}
