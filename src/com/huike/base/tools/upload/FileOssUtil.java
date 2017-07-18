package com.huike.base.tools.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListPartsRequest;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PartListing;
import com.aliyun.oss.model.PartSummary;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import com.huike.base.tools.CacheHelp;
import com.huike.base.tools.Pinyin4j;
import com.huike.base.tools.StringTools;

public class FileOssUtil {
	
	private static final Logger log = LoggerFactory.getLogger(FileOssUtil.class);
	 
	
	private static String endpoint = CacheHelp.SYS_CONFIG_MAP.get("enpoint")==null? "http://oss-cn-shanghai.aliyuncs.com": CacheHelp.SYS_CONFIG_MAP.get("enpoint").toString();  
    private static String accessKeyId = CacheHelp.SYS_CONFIG_MAP.get("access_key_id")==null? "LTAIATNJ4LJxL2kx":CacheHelp.SYS_CONFIG_MAP.get("access_key_id").toString();  
    private static String accessKeySecret = CacheHelp.SYS_CONFIG_MAP.get("access_key_secret")==null? "642CO92ednk0eqSY0zNkuDzk6FxJh2":CacheHelp.SYS_CONFIG_MAP.get("access_key_secret").toString();  
    private static String bucketName = CacheHelp.SYS_CONFIG_MAP.get("bucket_name")==null?"baichaung":CacheHelp.SYS_CONFIG_MAP.get("bucket_name").toString();    
	// private static OSSClient client = null;  
    
    private static String urlName="http://baichaung.oss-cn-shanghai.aliyuncs.com";
    
    private static String key = "*** Provide key ***";
    private static String localFilePath = "*** Provide local file path ***";
    
    private static String indexPath=CacheHelp.SYS_CONFIG_MAP.get("indexPath")==null?"weiside/":CacheHelp.SYS_CONFIG_MAP.get("indexPath").toString();
//    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
//    private static List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());
    /**
     * <p class="detail">
     * 功能：上传文件 分批上传较大的文件 
     * 参考了ali的上传用例DEMO 一次性上传的最大文件是5G 
     * 通过线程任务---分片分批上次---
     * </p>
     * @author renp
     * @date 2017年1月19日 
     * @param file
     * @return
     * @throws Exception 
     */
//    
//    public static String fileMultipartUpload(MultipartFile file) throws Exception {  
//        
//    	
//    	
//    	ClientConfiguration conf = new ClientConfiguration();
//        conf.setIdleConnectionTime(1000);
//        // 创建一个可重用固定线程数的线程池。若同一时间线程数大于10，则多余线程会放入队列中依次执行  
//        ExecutorService executorService = Executors.newFixedThreadPool(10);  
//        
//       // String ct=String.valueOf(System.currentTimeMillis())+String.valueOf((int)Math.random()*1000);
//        try {  
//        String ct=StringUtil.getUUID();
//        String pre=file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
//        String OriginalFilename=Pinyin4j.getPYName(pre, false, false) +ct;
//        String after=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
//              
//        String key = OriginalFilename+after; // 获取上传文件的名称，作为在OSS上的文件名  
//        key=key.replaceAll(" ", "");
//        // 创建OSSClient实例  
//        client = new OSSClient(endpoint, accessKeyId, accessKeySecret,conf);  
//        client.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
//       
//        	//初始化分片上传 (根据 )
//            String uploadId = OssRunnable.claimUploadId(bucketName, key);  
//            // 设置每块为 5M(除最后一个分块以外，其他的分块大小都要大于5MB)  
//            //final long partSize = 5 * 1024 * 1024L;  
//            //final long partSize = 1 * 1024 * 1024L;  
//            final long partSize = 1 * 1024 * 200L; 
//            // 计算分块数目  
//            long fileLength = file.getSize();  //得到文件大小
//            System.out.print("得到文件大小"+fileLength);
//            int partCount = (int) (fileLength / partSize);  
//            if (fileLength % partSize != 0) {  
//                partCount++;  
//            }    
//            // 阿里限制最大文件一次上传为5G 分块 号码的范围是1~10000。如果超出这个范围，OSS将返回InvalidArgument的错误码。  
//            if (partCount > 10000) {  
//                throw new RuntimeException("文件过大(分块大小不能超过10000)");  
//            } else {  
//                //logger.info("一共分了 " + partCount + " 块");  
//               System.out.print("一共分了 "+partCount +" 块");
//            }  
//  
//            /** 
//             * 将分好的文件块加入到list集合中 
//             */  
//            for (int i = 0; i < partCount; i++) {  
//                long startPos = i * partSize;  
//                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;  
//                  
//                // 线程执行。将分好的文件块加入到list集合中  
//                executorService.execute(new OssRunnable(file, startPos, curPartSize, i + 1, uploadId, key, bucketName));  
//            }  
//  
//            /** 
//             * 等待所有分片完毕 
//             */  
//            // 关闭线程池（线程池不马上关闭），执行以前提交的任务，但不接受新任务。  
//            executorService.shutdown();  
//            // 如果关闭后所有任务都已完成，则返回 true。  
//            while (!executorService.isTerminated()) {  
//                try {  
//                    // 用于等待子线程结束，再继续执行下面的代码  
//                    executorService.awaitTermination(5, TimeUnit.SECONDS);  
//                } catch (InterruptedException e) {  
//                    e.printStackTrace();  
//                }  
//            }  
//  
//            /** 
//             * partETags(上传块的ETag与块编号（PartNumber）的组合) 如果校验与之前计算的分块大小不同，则抛出异常 
//             */  
//            System.out.println(OssRunnable.partETags.size()  +" -----   "+partCount);  
//            if (OssRunnable.partETags.size() != partCount) {  
//                throw new IllegalStateException("上传失败 OSS分块大小与文件所计算的分块大小不一致");  
//            } else {  
//               // logger.info("将要上传的文件名  " + key + "\n");  
//               System.out.print("将要上传的文件名  " + key + "\n");
//            }  
//  
//            /* 
//             * 列出文件所有的分块清单并打印到日志中，该方法仅仅作为输出使用 
//             */  
//           // OssRunnable.listAllParts(uploadId);  
//  
//            /* 
//             * 完成分块上传 
//             */  
//            OssRunnable.completeMultipartUpload(uploadId);  
//              
//            // 返回上传文件的URL地址  
//            //return endpoint + "/" + bucketName + "/" + client.getObject(bucketName, key).getKey();  
//             return  urlName+"/" + client.getObject(bucketName, key).getKey();  
//             
//        } catch (Exception e) {  
//            //logger.error("上传失败！", e); 
//        	throw new RuntimeException("上传失败！");
//           // System.out.print("上传失败！"+e);
//          //	return "上传失败！";  
//        } finally {  
//        	//清空临时变量和关闭资料
//            OssRunnable.partETags.clear();  
//            if (client != null) {  
//                client.shutdown();  
//            }  
//        }  
//    }  
//    
    
    /**
     * <p class="detail">
     * 功能：简单的本地文件上传 
     * </p>
     * @author renp
     * @date 2017年1月19日 
     * @param uploadFile
     * @param key
     * @return
     */
    
    public static String samplesUpload(String uploadFile,String key ){
    	 OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    	 ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
         try {
             UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);
             // 待上传的本地文件
             uploadFileRequest.setUploadFile(uploadFile);
             // 设置并发下载数，默认1
             uploadFileRequest.setTaskNum(5);
             // 设置分片大小，默认100KB
             uploadFileRequest.setPartSize(1024 * 1024 * 1);
             // 开启断点续传，默认关闭
             uploadFileRequest.setEnableCheckpoint(true);
             
             UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);
             
             CompleteMultipartUploadResult multipartUploadResult = 
                     uploadResult.getMultipartUploadResult();
             System.out.println(multipartUploadResult.getETag());
             
            
         } catch (OSSException oe) {
             System.out.println("Caught an OSSException, which means your request made it to OSS, "
                     + "but was rejected with an error response for some reason.");
             System.out.println("Error Message: " + oe.getErrorCode());
             System.out.println("Error Code:       " + oe.getErrorCode());
             System.out.println("Request ID:      " + oe.getRequestId());
             System.out.println("Host ID:           " + oe.getHostId());
         } catch (ClientException ce) {
             System.out.println("Caught an ClientException, which means the client encountered "
                     + "a serious internal problem while trying to communicate with OSS, "
                     + "such as not being able to access the network.");
             System.out.println("Error Message: " + ce.getMessage());
         } catch (Throwable e) {
             e.printStackTrace();
         } finally {
             ossClient.shutdown();
         }
         return endpoint + "/" + bucketName + "/" + ossClient.getObject(bucketName, key).getKey();
         
    }
    
    /**
     *
     *<p class="detail">
     * 功能：简单上传 通过字节数组方式
     * </p>
     * @author renp
     * @date 2017年3月4日 
     * @param uploadFile
     * @param key
     * @return
     */
    
    public static String samplesUploadByByte(MultipartFile uploadFile,String key ){
    	   
    // endpoint以杭州为例，其它region请按实际情况填写
//    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//    // accessKey请登录https://ak-console.aliyun.com/#/查看
//    String accessKeyId = "<yourAccessKeyId>";
//    String accessKeySecret = "<yourAccessKeySecret>";
    // 创建OSSClient实例
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
  	 ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);

    // 上传
    //byte[] content = "Hello OSS".getBytes();
    try {
		ossClient.putObject(bucketName, key, new ByteArrayInputStream(uploadFile.getBytes()));
	} catch (OSSException | ClientException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // 关闭client
    //ossClient.shutdown();
    return endpoint + "/" + bucketName + "/" + ossClient.getObject(bucketName, key).getKey();
    
    }
    
    /**
     * <p class="detail">
     * 功能：简单的文件下载
     * </p>
     * @author renp
     * @date 2017年3月11日 
     * @param uploadFile
     * @param key
     * @return
     */
    
    public static DownloadFileResult samplesDown(String key ){
 	   
        // endpoint以杭州为例，其它region请按实际情况填写
//        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//        // accessKey请登录https://ak-console.aliyun.com/#/查看
//        String accessKeyId = "<yourAccessKeyId>";
//        String accessKeySecret = "<yourAccessKeySecret>";
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
        
      	 ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);

     // 下载请求，10个任务并发下载，启动断点续传
        DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, key);
        downloadFileRequest.setDownloadFile("downloadFile");
        downloadFileRequest.setTaskNum(10);
        downloadFileRequest.setEnableCheckpoint(true);
        // 下载文件
        DownloadFileResult downloadRes=null;
		try {
			downloadRes = ossClient.downloadFile(downloadFileRequest);
	        // 下载成功时，会返回文件的元信息
	        downloadRes.getObjectMetadata();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 关闭client
        ossClient.shutdown();
		return downloadRes;
       
        }
      
    
    /**
     * <p class="detail">
     * 功能：下载文件到本地磁盘
     * </p>
     * @author renp
     * @date 2017年3月21日 
     * @param key
     * @return
     */
    public static File downLocalFile(String key){
    // endpoint以杭州为例，其它region请按实际情况填写
//    int ret=0;
//    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//    // accessKey请登录https://ak-console.aliyun.com/#/查看
//    String accessKeyId = "<yourAccessKeyId>";
//    String accessKeySecret = "<yourAccessKeySecret>";
//    String bucketName = "<yourBucketName>";
    // 创建OSSClient实例
    	
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
    
 	 ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);

    
  //  OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    // 下载object到文件
 	System.out.println("输入的KEY="+key);
 	int index=key.toString().lastIndexOf("/");
	String fileName=key.toString().substring(index+1, key.toString().length());
	
	File f=new File(GetFileUrl.getFileUrl()+fileName); //
    ossClient.getObject(new GetObjectRequest(bucketName, indexPath+"/"+key), f);
    // 关闭client
    ossClient.shutdown();
    return f;
    }
    
    
    /**
     * <p class="detail">
     * 功能：上传文件第2版本
     * </p>
     * @author renp
     * @date 2017年5月5日 
     * @param multipartFile
     * @param pathPreName
     * @return
     * @throws Exception 
     */
    
    public String uploadFile(MultipartFile multipartFile) throws Exception {
    	 String url="";
    	
    	/*CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        String fileName = multipartFile.getOriginalFilename();*/
        
        String ct=StringTools.getUUID();
        String pre=multipartFile.getOriginalFilename().substring(0, multipartFile.getOriginalFilename().lastIndexOf("."));
        String OriginalFilename=Pinyin4j.getPYName(pre, false, false) +ct;
        String after=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."), multipartFile.getOriginalFilename().length());              
        String key = OriginalFilename+after; // 获取上传文件的名称，作为在OSS上的文件名  
        key=indexPath+"/"+key.replaceAll(" ", "");
        
        //上传域名
        String endpoint = CacheHelp.SYS_CONFIG_MAP.get("enpoint")==null? "http://oss-cn-shanghai.aliyuncs.com": CacheHelp.SYS_CONFIG_MAP.get("enpoint").toString();  
    	//上传keyId
    	String accessKeyId = CacheHelp.SYS_CONFIG_MAP.get("access_key_id")==null? "LTAIATNJ4LJxL2kx":CacheHelp.SYS_CONFIG_MAP.get("access_key_id").toString();  
        //上传keySecret
        String accessKeySecret = CacheHelp.SYS_CONFIG_MAP.get("access_key_secret")==null? "642CO92ednk0eqSY0zNkuDzk6FxJh2":CacheHelp.SYS_CONFIG_MAP.get("access_key_secret").toString();  
        //上传Bucket
        String bucketName = CacheHelp.SYS_CONFIG_MAP.get("bucket_name")==null?"baichaung":CacheHelp.SYS_CONFIG_MAP.get("bucket_name").toString();
        
        //上传key
        //String key = pathPreName + "/" +StringUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."), fileName.length());

        /*
         * Constructs a client instance with your account for accessing OSS
         */
        ClientConfiguration conf = new ClientConfiguration();
        conf.setIdleConnectionTime(1000);
        OSSClient  client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);

        boolean exists = client.doesBucketExist(bucketName);
        if (!exists) {
        	client.createBucket(bucketName);
        }
        //设置Bucket权限为公开可读
        client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

        try {
            /*
             * Claim a upload id firstly
             */
            String uploadId = claimUploadId(bucketName, key,client);

            /*
             * Calculate how many parts to be divided
             */
            final long partSize = 1 * 1024 * 200L; // 5MB 200K
            long fileLength = multipartFile.getSize();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                //最大切片数不能超过10000
                throw new RuntimeException("文件过大(分块大小不能超过10000)");  
            } else {
            	log.info("上传文件：" + multipartFile.getName() + "共切片" + partCount);
            }

            /*
             * Upload multiparts to your bucket
             */
            //            System.out.println("Begin to upload multiparts to OSS from a file\n");
            log.info("开始上传");
            List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                if (!executorService.isShutdown()) {
                    executorService.execute(new PartUploader(multipartFile, startPos, curPartSize, i + 1,
                        uploadId, bucketName, key, partETags,client));
                }
            }

            /*
             * Waiting for all parts finished
             */
            if (!executorService.isShutdown()) {
                executorService.shutdown();
            }
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /*
             * Verify whether all parts are finished
             */
            if (partETags.size() != partCount) {
            	log.error("上传失败，因为还有一些没上传完");
                throw new IllegalStateException(
                                   "Upload multiparts fail due to some parts are not finished yet");
            	
                
            } else {
                //                System.out.println("Succeed to complete multiparts into an object named " + key
                //                                   + "\n");
            	log.info("所有切片全部上传成功，文件名：" + key);
            }

            /*
             * View all parts uploaded recently
             */
            //listAllParts(uploadId, bucketName, key);

            /*
             * Complete to upload multiparts
             */
            completeMultipartUpload(uploadId, bucketName, key, partETags,client);

            /*
             * Fetch the object that newly created at the step below.
             */
            System.out.println("Fetching an object");
            //注释 client.getObject(new GetObjectRequest(bucketName, key), file);
            url=FileOssUtil.urlName+"/" + client.getObject(bucketName, key).getKey();
            log.info("返回的参数为"+ FileOssUtil.urlName+"/" + client.getObject(bucketName, key).getKey());
            
        } catch (OSSException oe) {
        	log.error("Caught an OSSException, which means your request made it to OSS, "
                         + "but was rejected with an error response for some reason.");
        	log.error("Error Message: " + oe.getMessage());
        	log.error("Error Code:       " + oe.getErrorCode());
        	log.error("Request ID:      " + oe.getRequestId());
        	log.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
        	log.error("Caught an ClientException, which means the client encountered "
                         + "a serious internal problem while trying to communicate with OSS, "
                         + "such as not being able to access the network.");
        	log.error("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            if (client != null) {
            	client.shutdown();
            }
        }
        return  url;  

    }

    private String claimUploadId(String bucketName, String key,OSSClient client) {
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
        InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
        return result.getUploadId();
    }

    private class PartUploader implements Runnable {

        private MultipartFile           localFile;
        private long           startPos;
        private List<PartETag> partETags;

        private long           partSize;
        private int            partNumber;
        private String         uploadId;
        private String         bucketName;
        private String         key;
        private OSSClient      client;

        public PartUploader(MultipartFile localFile, long startPos, long partSize, int partNumber,
                            String uploadId, String bucketName, String key, List<PartETag> partETags,OSSClient      client) {
            this.localFile = localFile;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
            this.bucketName = bucketName;
            this.key = key;
            this.partETags = partETags;
            this.client=client;
        }

        @Override
        public void run() {
            InputStream instream = null;
            try {
                instream = this.localFile.getInputStream();
                instream.skip(this.startPos);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(key);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);

                UploadPartResult uploadPartResult = this.client.uploadPart(uploadPartRequest);
                System.out.println("Part#" + this.partNumber + " done\n");
                synchronized (partETags) {
                    partETags.add(uploadPartResult.getPartETag());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void listAllParts(String uploadId, String bucketName, String key,OSSClient client) {
    	log.info("Listing all parts......");
        ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, key, uploadId);
        PartListing partListing = client.listParts(listPartsRequest);

        int partCount = partListing.getParts().size();
        for (int i = 0; i < partCount; i++) {
            PartSummary partSummary = partListing.getParts().get(i);
            System.out.println("\tPart#" + partSummary.getPartNumber() + ", ETag="
                               + partSummary.getETag());
        }
        System.out.println();
    }

    private void completeMultipartUpload(String uploadId, String bucketName, String key,
                                         List<PartETag> partETags,OSSClient      client) {
        // Make part numbers in ascending order
        Collections.sort(partETags, new Comparator<PartETag>() {

            @Override
            public int compare(PartETag p1, PartETag p2) {
                return p1.getPartNumber() - p2.getPartNumber();
            }
        });

        log.info("Completing to upload multiparts\n");
        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
            bucketName, key, uploadId, partETags);
        client.completeMultipartUpload(completeMultipartUploadRequest);
    }
    
    
    
    
    
}
