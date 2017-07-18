package com.huike.app.upload.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huike.app.upload.model.ResourceFile;
import com.huike.app.upload.service.impl.ResourceFileService;
import com.huike.base.tools.MapUtil;
import com.huike.base.tools.upload.FileOssUtil;

@RestController
@RequestMapping("/api/web/rest/file")
public class UploadingController {

	@Autowired
	private ResourceFileService resourceFileService;

	protected static final Logger log = LoggerFactory.getLogger(UploadingController.class);

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> uploadingPost(HttpServletRequest request,
			@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> rowData = new HashMap<String, Object>();
		Long userid = 1l;
		resultMap.put("resultCode", 1);
		String filesize = "10";
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/" + "wsd/upload" + "/";
		// log.info("根目录:" + basePath);
		System.out.print(basePath);

		System.out.println("项目的路径" + request.getContextPath());

		System.out.println(request.getRealPath("/"));

		String realPath = request.getSession().getServletContext().getRealPath("/upload");

		List<String> filesPath = new ArrayList<String>();
		ResourceFile files = new ResourceFile();
		List<ResourceFile> list = new ArrayList<ResourceFile>();
		int x = 0;

		for (MultipartFile myfile : uploadingFiles) {
			if (myfile.isEmpty()) {
				log.info("文件未上传");
				request.setAttribute("flag", "0");
				resultMap.put("resultMsg", "第" + (x + 1) + "个文件上传失败");
				return resultMap;
			} else {
				System.out.println("开始处理的时间毫秒为" + System.currentTimeMillis());
				Long fsize = myfile.getSize() / 1024;
				log.info("文件大小：" + fsize);
				String url = "";
				String foldname = myfile.getOriginalFilename();// 源文件名
				String ftype = foldname.substring(foldname.lastIndexOf("."), foldname.length()); // 文件的扩展名
				try {
					url =  new FileOssUtil().uploadFile(myfile);// 调用Oss的分片上传大文件到OSS存储服务
					rowData.put("url", url);
					rowData.put("fileName", foldname);
				} catch (Exception e) {
					resultMap.put("resultMsg", "文件上传失败");
					return resultMap;
				}

				files = new ResourceFile();
				// 设置文件的 时间 大小 等属性
				files.setSourceName(myfile.getName());
				files.setSourceOldName(foldname);
				files.setSourceSize(Math.round(myfile.getSize() / 1024) / 100.0);// 文件大小单位M
				// url=basePath; // 保留两位小数
				files.setSourceSuffix(ftype);
				files.setSourceUrl(url);
				files.setSourceUserid(userid);
				x++;
				resourceFileService.save(MapUtil.transBean2Map(files));
				list.add(files);
				System.out.println("结束的时候处理的时间毫秒为" + System.currentTimeMillis());
				resultMap.put("rowData", rowData);
			}

		}
		resultMap.put("resultCode", 0);
		resultMap.put("resultMsg", "文件上传成功");
		return resultMap;
	}
}
