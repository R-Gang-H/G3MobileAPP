package com.app.itserv;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.app.itserv.activity.LoginActivity;
import com.itserv.app.util.CrashHandler;
import com.itserv.app.util.Log4jUtil;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yycloud.app.db.EventDBHelper;
import com.yycloud.app.db.ShedStrategyDBHelper;
import com.yycloud.app.db.StrategyDBHelper;
import com.yycloud.app.utils.TAPreferenceUtil;
import com.yycloud.app.utils.WAPI;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.app.utils.WapiUtilEx;
import com.yycloud.core.beans.CameraStatus;
import com.yycloud.core.cache.GlobalConst;
import com.yycloud.core.config.Constants;
import com.yycloud.core.config.DeviceType;

import org.apache.log4j.Level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.itserv.app.http.HttpUtils.token;

/**
 * @author haoruigang
 *         2017???9???13???16:34:35
 */
public class MineApplication extends Application {
    // DisplayImageOptions?????????????????????????????????
    private static Context mContext;
    public static DisplayImageOptions default_img;

    private static MineApplication mInstance = null;

    public static int mVersionCode;
    public static String mVersionName;

//    public static Socket socket;

    public static Context getContext() {
        return mContext;
    }

    public static MineApplication getInstance() {
        return mInstance;
    }

    // SDCard??????
    public static final String SD_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    // ??????????????????
    public static final String BASE_PATH = SD_PATH + "/zsnc/images";

    // ??????????????????
    public static final String BASE_IMAGE_CACHE = BASE_PATH + "cache/images/";
    public static ImageLoaderConfiguration config;
    public static ExecutorService executorService = null;
    public static NotificationCompat.Builder mBuilder;
    public static NotificationManager mNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        // ??????????????????
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        // ????????? SDK ???????????????????????? context ??????????????? ApplicationContext
        config = getConfig(getApplicationContext());
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        default_img = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.loading_thumb) // ???????????????????????????????????????
                .showImageForEmptyUri(R.drawable.loading_thumb) // ????????????Uri??????????????????????????????????????????
                .showImageOnFail(R.drawable.loading_thumb) // ???????????????????????????????????????????????????????????????
                .cacheInMemory(true) // ?????????????????????????????????????????????
                .cacheOnDisc(true) // ????????????????????????????????????SD??????
                // .displayer(new RoundedBitmapDisplayer(30)) // ?????????????????????
                .build();
        // ?????????????????????
        mInstance = this;
        mContext = this;
        // note MyVolley.init(this);
        WapiUtilEx.init(this);
        StrategyDBHelper.Initialize(this);
        EventDBHelper.Initialize(this);
        ShedStrategyDBHelper.Initialize(this);

        PackageInfo pinfo;
        try {
            pinfo = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            mVersionCode = pinfo.versionCode;
            mVersionName = pinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

//        try {
////            socket = IO.socket(WAPI.WAPI_HTTP_IO);
//        } catch (URISyntaxException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }

        super.onCreate();
    }

    private void init() {
        initExecut();
        //oss????????????start//  ??????????????????   ?????????  2017???9???21??? 17:47:02
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()   // or .detectAll() for all detectable problems
//                .penaltyLog()
//                .build());
//
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects()
//                .detectLeakedClosableObjects()
//                .penaltyLog()
//                .penaltyDeath()
//                .build());
    }

    private void initExecut() {
        setupLogger();
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(10);
        }
    }

    /**
     * ????????????log4j
     */
    private void setupLogger() {
        String fileName = Constants.LOG_PATH + Constants.LOG_FILE_NAME;
        Log4jUtil.exportLogToFile(fileName, Level.DEBUG, (long) (1024 * 1024 * 5), 200, true);
    }

    public static ImageLoaderConfiguration getConfig(Context context) {
        String cacheDisc = BASE_IMAGE_CACHE;
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, cacheDisc);
        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc().build();
        config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(5).threadPriority(Thread.NORM_PRIORITY - 1)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(1024 * 1024))
                .memoryCacheSize(1 * 1024 * 1024)
                .discCache(new UnlimitedDiscCache(cacheDir))
                // default
                .discCacheSize(20 * 1024 * 1024).discCacheFileCount(400)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(imageOptions) // default
                .build();
        return config;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
//        socket.disconnect();
        token = null;
        executorService.shutdown();
        clearAllNotify();
        super.onTerminate();
    }

    public MineApplication() {
        super();
    }

    /**
     * ????????????
     *
     * @param ser
     * @param file
     * @throws IOException
     */
    public boolean saveObject(Serializable ser, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(file, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * ????????????????????????
     *
     * @param cachefile
     * @return
     */
    private boolean isExistDataCache(String cachefile) {
        boolean exist = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

    /**
     * ????????????
     *
     * @param file
     * @return
     * @throws IOException
     */
    public Serializable readObject(String file) {
        if (!isExistDataCache(file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            // ?????????????????? - ??????????????????
            if (e instanceof InvalidClassException) {
                File data = getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static int getComponentIcon(String type, String extendType,
                                       String online, String componentDevName) {
        // 1????????????
        if (type.equals(DeviceType.erelay)) {
            if (extendType.equals(DeviceType.erelay_water_valve)) { // ??????
                return R.drawable.water_valve;
            } else if (extendType.equals(DeviceType.erelay_lamp)) { // ???
                return R.drawable.lamp;
            } else if (extendType.equals(DeviceType.erelay_exhaust)) {//??????
                return R.drawable.remove;
            }
            // 2????????????
        } else if (type.equals(DeviceType.erelay2)) {
            /*
             * if (componentDevName.equals("???????????????")) { return R.drawable.remove;
			 * }
			 */
            if (extendType.equals(DeviceType.erelay2_shutter)) {
                // ?????????
                return R.drawable.shutter;
            } else if (extendType.equals(DeviceType.erelay_ventilation)) { // ?????????
                return R.drawable.ventilation;
            }

            // 3?????????
        } else if (type.equals(DeviceType.cameraip)) {
            if (online.equals("1")) { // ??????????????????
                return R.drawable.camera_online;
            } else {
                return R.drawable.camera_offline;
            }
        } else if (type.equals(DeviceType.camera)) { // ????????????
            return R.drawable.camera;
            // 4?????????
        } else if (type.equals(DeviceType.humidity_temperature)) { // ????????????????????????
            return R.drawable.temperature_humidity;
        } else if (type.equals(DeviceType.illumination)) { // ???????????????
            return R.drawable.illumination;
            // ???????????????
        } else if (type.equals(DeviceType.soil_th)) {
            return R.drawable.soiltem;
            // ?????????
        } else if (type.equals(DeviceType.relaybox)) {
            return R.drawable.realybox;
        }
        return R.drawable.unknown;
    }

    public static CameraStatus getState(String online, String status) {
        if (online.equals("1")) {
            if (status == null || status.equals("0")) {
                CameraStatus cameraStatus = new CameraStatus();
                cameraStatus.iconResId = R.drawable.open;
                cameraStatus.text = "??????";
                cameraStatus.val = online;
                cameraStatus.btn_icon = R.drawable.button_close;
                return cameraStatus;
            } else {
                CameraStatus cameraStatus = new CameraStatus();
                cameraStatus.iconResId = R.drawable.open;
                cameraStatus.text = "??????";
                cameraStatus.val = online;
                cameraStatus.btn_icon = R.drawable.button_open;
                return cameraStatus;
            }
        } else {
            CameraStatus cameraStatus = new CameraStatus();
            cameraStatus.iconResId = R.drawable.close;
            cameraStatus.text = "??????";
            cameraStatus.val = online;
            cameraStatus.btn_icon = R.drawable.button_close;
            return cameraStatus;
        }
    }


    public static String getDeviceInfo(String devUuid) {
        String msg = WapiUtil.getDeviceInfo(devUuid);
        // String url = WAPI.WAPI_DEVICE_INFO_URL + "?dev_uuid=" + devUuid;
        String url = WAPI.WAPI_USER_SMARTGATE_URL + devUuid + WAPI.INFO_URL;

        MineApplication application = MineApplication.getInstance();
        String filename = GlobalConst.Md5(url + WapiUtil.getSessionToken());
        if (msg == null || msg.equals("")) {
            msg = (String) application.readObject(filename);
        } else {
            application.saveObject(msg, filename);
        }
        return msg;
    }

    /**
     * ??????sessionToken
     *
     * @return
     */
    public static String getSessionToken() {
        TAPreferenceUtil preferenceUtil = TAPreferenceUtil
                .getInstance(MineApplication.getContext());
        String session_token = preferenceUtil.getSessionToken();
        return session_token;
    }

    /**
     * ????????????????????????
     *
     * @return
     */
    public static String getUserPsd() {
        TAPreferenceUtil preferenceUtil = TAPreferenceUtil
                .getInstance(MineApplication.getContext());
        String session_token = preferenceUtil.getPsd();
        return session_token;
    }

    /**
     * ???????????????????????????
     *
     * @return
     */
    public static String getUserName() {
        TAPreferenceUtil preferenceUtil = TAPreferenceUtil
                .getInstance(MineApplication.getContext());
        String session_token = preferenceUtil.getUser();
        return session_token;
    }

    public static String getAnalysisResult(String sn, String type, String scope) {
        String msg = WapiUtil.getAnalysisResult(sn, type, scope);
        Log.v("connect", "sn:" + sn + ",device_type:" + type + ",msg:" + msg);

        String url = WapiUtil.getAnalysisResultUrl(sn, type, scope);
        Log.v("connect url", "device_type:" + type + "url:" + url);
        MineApplication application = MineApplication.getInstance();
        String filename = GlobalConst.Md5(url);
        if (msg == null || msg.equals("")) {
            msg = (String) application.readObject(filename);
        } else {
            application.saveObject(msg, filename);
        }
        return msg;
    }

    private static String oldMsg;
    /**
     * ???????????????
     */
    private static long oneTime = 0;
    /**
     * ???????????????
     */
    private static long twoTime = 0;


    /**
     * ?????????????????????????????????
     */
    private void initService() {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
    }

    /**
     * ??????????????????????????????
     */
    public void clearNotify(int notifyId) {
        mNotificationManager.cancel(notifyId);// ???????????????????????????ID???????????????
        // mNotification.cancel(getResources().getString(R.string.app_name));
    }

    /**
     * ?????????????????????
     */
    public static void clearAllNotify() {
        mNotificationManager.cancelAll();// ???????????????????????????
    }

    /**
     * @???????????????pendingIntent,????????????2.3?????????????????????
     * @flags??????: ???????????????:Notification.FLAG_ONGOING_EVENT ???????????????
     * Notification.FLAG_AUTO_CANCEL
     */
    public static PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1,
                new Intent(), flags);
        return pendingIntent;
    }
}

