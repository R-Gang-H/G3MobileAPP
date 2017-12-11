package com.alicloud.oss.token;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alicloud.oss.model.Paramet;
import com.app.itserv.MineApplication;
import com.itserv.app.util.EmptyUtils;
import com.itserv.app.util.FileUtils;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.text.SimpleDateFormat;

/**
 * 初始化阿里云
 *
 * @author Eason
 * @time 2017/9/21.
 * @des
 */

public class Initialicloud {

    private static volatile Initialicloud instance = null;

    public Initialicloud() {
    }

    public static Initialicloud getInstance() {
        synchronized (Initialicloud.class) {
            if (instance == null) {
                instance = new Initialicloud();
            }
        }
        return instance;
    }


    /**
     * oss接口api的入口
     * 目前所有api接口传入的callback接口回调都是在子线程，
     * 底层并没有进行线程切换（子线程切换到ui线程），这个目前需要调用者自行来控制！
     */
    private OSS oss;

    private OSSCredentialProvider mCredentialProvider;

    public void setOssClient(String ak, String sk, String token) {
        if (mCredentialProvider == null || oss == null) {
            // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考访问控制章节
            // 也可查看sample 中 sts 使用方式了解更多(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
            mCredentialProvider = new OSSStsTokenCredentialProvider(ak, sk, token);
            OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(ak, sk, token);
            //该配置类如果不设置，会有默认配置，具体可看该类
            ClientConfiguration conf = new ClientConfiguration();
            conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
            conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
            conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
            conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
            //通过调用OSSLog.enableLog()开启可以在控制台看到日志，
            //并且会支持写入手机sd卡中的一份日志文件位置在SDCard_path\OSSLog\logs.csv  默认不开启
            //日志会记录oss操作行为中的请求数据，返回数据，异常信息
            //例如requestId,response header等,下边是一个日志记录case
            //android_version：5.1  android版本
            //mobile_model：XT1085  android手机型号
            //network_state：connected  网络状况
            //network_type：WIFI 网络连接类型
            //具体的操作行为信息:
            //[2017-09-05 16:54:52] - Encounter local execpiton: //java.lang.IllegalArgumentException: The bucket name is invalid.
            //A bucket name must:
            //1) be comprised of lower-case characters, numbers or dash(-);
            //2) start with lower case or numbers;
            //3) be between 3-63 characters long.
            //------>end of log
            OSSLog.enableLog(); //这个开启会支持写入手机sd卡中的一份日志文件位置在SD_path\OSSLog\logs.csv
            oss = new OSSClient(MineApplication.getContext(), Paramet.endpoint, credentialProvider, conf);
            initSamples();
        } else {
            ((OSSStsTokenCredentialProvider) mCredentialProvider).setAccessKeyId(ak);
            ((OSSStsTokenCredentialProvider) mCredentialProvider).setSecretKeyId(sk);
            ((OSSStsTokenCredentialProvider) mCredentialProvider).setSecurityToken(token);
        }
    }

    //压缩图片
    public static void compressBitmap(String bitmap, String imagePath, Bitmap.Config
            config, FileCallback callback) {
        FileUtils.gcAndFinalize();
        Tiny.FileCompressOptions compressOptions = new Tiny.FileCompressOptions();
        compressOptions.config = config;
        if (EmptyUtils.isNotEmpty(imagePath))
            compressOptions.outfile = imagePath;
        Tiny.getInstance().source(bitmap).asFile().withOptions(compressOptions).compress(callback);
    }

//    public String initSamples() {
//        final String[] o = {""};
//        compressBitmap(Paramet.uploadFilePath, Paramet.uploadFilePath, Bitmap.Config.RGB_565, new FileCallback() {
//            @Override
//            public void callback(boolean isSuccess, String outfile) {
//                if (isSuccess) {
//                    o[0] = outfile;
//                }
//            }
//        });
//        return o[0];
//    }


    public PutObject putObjectSamples;

    public void initSamples() {
        if (oss != null && Paramet.testBucket != null && Paramet.uploadObject != null && Paramet.uploadFilePath != null) {
            putObjectSamples = new PutObject(oss, Paramet.testBucket, Paramet.uploadObject, Paramet.uploadFilePath);
        } else {
            toast("---上传缺少参数，请检查---");
        }
    }

    public void toast(String msg) {
        Toast.makeText(MineApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public String getDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }
}
