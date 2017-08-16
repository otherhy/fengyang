package com.scorpio.fengyang.tools;

import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by hao on 2017/8/16.
 */
public class UploadFileTool {
    public static String  upload_file(byte[] fileByte,String fileName) throws Exception {
        //要先读取配置文件
        ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
        ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());

        //创建跟踪服务器客户端
        TrackerClient trackerClient = new TrackerClient();

        //获取跟踪服务器服务器端
        TrackerServer connection = trackerClient.getConnection();

        //脑洞清奇的余庆说文件服务器客户端也要new
        StorageClient1 storageClient1 = new StorageClient1(connection, null);

        String extension = fileName.substring(fileName.lastIndexOf("."));

        //上传文件
        String path = storageClient1.upload_file1(fileByte, extension, null);

        return path;
    }
}
