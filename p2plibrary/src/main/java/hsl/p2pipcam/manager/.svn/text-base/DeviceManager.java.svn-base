/**
 *
 */
package hsl.p2pipcam.manager;

import android.content.Context;
import android.util.Log;

import hsl.p2pipcam.manager.listener.DeviceStatusListener;
import hsl.p2pipcam.manager.listener.PlayListener;
import hsl.p2pipcam.manager.listener.RecorderListener;

/**
 * 璁惧绠＄悊鍣�
 *
 * @author wang.jingui
 */
public class DeviceManager {
    //鐩戝惉鍥炶皟鎺ュ彛
    private DeviceStatusListener deviceStatusListener;
    private PlayListener playListener;
    private RecorderListener recorderListener;
    //璁惧鍒楄〃
    //涓婁笅鏂�
    private Context context;
    private WvrDeviceManager wvrDeviceManager;
    private static DeviceManager instance;

    public static DeviceManager getDeviceManager(Context context) {
        if (instance == null)
            instance = new DeviceManager(context);
        return instance;
    }

    public DeviceManager(Context context) {
        this.context = context;
        wvrDeviceManager = new WvrDeviceManager(this);
    }

    public void initialize() {

        DeviceSDK.initialize("");
        DeviceSDK.setCallback(this);
        DeviceSDK.networkDetect();

        WvrDeviceSDK.initializeWvr("");
        WvrDeviceSDK.setWvrCallback(wvrDeviceManager);
        WvrDeviceSDK.NetworkDetect();

    }

    public void unInitSearchDevice() {
        DeviceSDK.unInitSearchDevice();
    }


    public void setDeviceStatusListener(DeviceStatusListener deviceStatusListener) {
        this.deviceStatusListener = deviceStatusListener;
        wvrDeviceManager.setDeviceStatusListener(deviceStatusListener);
    }

    public void setPlayListener(PlayListener playListener) {
        this.playListener = playListener;
    }


    public void setRecorderListener(RecorderListener recorderListener) {
        this.recorderListener = recorderListener;
    }

    //-------------------------------------------------------------------------
    //---------------------------浠ヤ笅鏄疛NI灞傚洖璋冪殑鎺ュ彛-------------------------------
    //-------------------------------------------------------------------------

    public void CallBack_SnapShot(long UserID, byte[] buff, int len) {
    }


    public void CallBack_GetParam(long UserID, long nType, String param) {
    }


    public void CallBack_SetParam(long UserID, long nType, int nResult) {

    }

    public void CallBack_Event(long UserID, long nType) {
        int status = new Long(nType).intValue();
        if (deviceStatusListener != null)
            deviceStatusListener.receiveDeviceStatus(UserID, status);
        Log.v("camerastatus", String.valueOf(status));
    }

    public void VideoData(long UserID, byte[] VideoBuf, int h264Data, int nLen, int Width, int Height, int time) {

    }

    public void callBackAudioData(long nUserID, byte[] pcm, int size) {
        if (playListener != null)
            playListener.callBackAudioData(nUserID, pcm, size);
        if (recorderListener != null)
            recorderListener.callBackAudioData(nUserID, pcm, size);
    }

    public void CallBack_RecordFileList(long UserID, int filecount, String fname, String strDate, int size) {
    }

    //

    /**
     * add by liuqh20170228 为了解决报错的问题：没有生效。。。
     * java.lang.NoSuchMethodError: no method with name='CallBack_RecordFileListV2' signature='(JLjava/lang/String;)V' in class Lhsl/p2pipcam/manager/DeviceManager;
     * at hsl.p2pipcam.nativecaller.NativeCaller.SetCallBack(Native Method)
     * at hsl.p2pipcam.manager.DeviceSDK.setCallback(DeviceSDK.java:41)
     * at hsl.p2pipcam.manager.DeviceManager.initialize(DeviceManager.java:45)
     * at hsl.p2pipcam.service.BridgeService$1.run(BridgeService.java:41)
     * <p>
     * <p>
     * CallBack_RecordFileList2(long UserID, int filecount, String fname, String strDate, int size)
     * <p>
     * <p>
     * update  周作威 2017年9月15日 11:05:52   之前不生效是因为参数不对
     * '(JLjava/lang/String;)V'代表参数和返回只，括号中为参数，括号外为返回值
     * 参数为JLjava/lang/String，第一个字母J代表long类型，第二个字母L代表object对象，java/lang/String代表对象在java中的包名，因此是String类型
     * 括号外的V为返回值，V代表void，无返回
     */
    public void CallBack_RecordFileListV2(long UserID, String fname) {

    }

    /**
     * 周作威 2017年9月15日 11:21:18
     * 解决java.lang.NoSuchMethodError
     *
     * @param nUserID
     * @param data
     * @param type
     */
    public void CallBack_RecordPicture(long nUserID, byte[] data, int type) {
    }

    public void CallBack_P2PMode(long UserID, int nType) {
    }

    public void CallBack_RecordPlayPos(long userid, int pos) {
    }

    public void CallBack_VideoData(long nUserID, byte[] data, int type, int size) {
    }

    public void CallBack_AlarmMessage(long UserID, int nType) {
    }


    public void showNotification(String message, Device device, int nType) {
    }
}
