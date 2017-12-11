package com.alicloud.oss.model;

/**
 * @author Eason
 * @time 2017/9/19.
 * @des
 */

public class Paramet {


    // To run the sample correctly, the following variables must have valid values.
    // The endpoint value below is just the example. Please use proper value according to your region.
    public static final String endpoint = "http://oss-cn-beijing.aliyuncs.com";//OSS链接端口
    public static String uploadFilePath = "";//上传文件路径
    public static final String testBucket = "yyossbucket";//存储空间名称
    public static String uploadObject = "";//上传对象
    public static final String downloadObject = "";//下载对象
    /**
     * config by local ip and port
     */
    public static final String STS_SERVER_API = "http://101.201.111.160:8188";//应用服务器
    //可以启动本地sts 服务来活动sts信息。详细见sts_local_server中的说明。

    private final String DIR_NAME = "oss";//文件
    private final String FILE_NAME = "caifang.m4a";//文件名


    public static final int UPLOAD_SUC = 3;
    public static final int UPLOAD_Fail = 4;
    public static final int UPLOAD_PROGRESS = 5;
    public static final int STS_TOKEN_SUC = 13;
    public static final int FAIL = 9999;


}
