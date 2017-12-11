package com.app.itserv.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.zxing.camera.CameraManager;
import com.zxing.decoding.CaptureActivityHandler;
import com.zxing.decoding.InactivityTimer;
import com.zxing.view.ViewfinderView;

import java.io.IOException;
import java.util.Vector;

/**
 * 二维码
 *
 * @author Administrator
 */
public class CaptureActivity extends FragmentActivity implements Callback {

    private Context mContext;

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        mContext = this;
        // 初始化 CameraManager
        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(CaptureActivity.this);
    }

    public void onResume() {
        super.onResume();
        init();
    }

    public void onPause() {
        super.onPause();
        // App.tongji();
        clones();
    }


    private void init() {
        /**
         * 这里就是一系列初始化相机view的过程
         */
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(CaptureActivity.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        /**
         * 这里就是看看是否是正常声音播放模式，如果是就播放声音，如果不是，则不播放
         */
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        /**
         * 初始化声音
         */
        initBeepSound();
        /**
         * 是否震动
         */
        vibrate = true;
    }

    private void clones() {
        /**
         * 关掉相机，关掉解码线程，清空looper队列中message
         */
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();// 关掉相机
    }

    @Override
    protected void onDestroy() {
        if (null != inactivityTimer) {
            inactivityTimer.shutdown();
        }
        super.onDestroy();
    }

    /**
     * 初始化相机
     */
    private void initCamera(final SurfaceHolder surfaceHolder) {
        new Handler().post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    CameraManager.get().openDriver(surfaceHolder);
                } catch (IOException ioe) {
                    return;
                } catch (RuntimeException e) {
                    return;
                }
                if (handler == null) {
                    /**
                     * 新建解码结果handler
                     */
                    handler = new CaptureActivityHandler(CaptureActivity.this, decodeFormats, characterSet);
                }
            }
        });
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        /**
         * 初始化相机
         */
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    /**
     * 返回显示的view
     */
    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    /**
     * 返回处理解码结果的handler
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * 清空view中先前扫描成功的图片
     */
    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    public void handleDecode(Result obj, Bitmap barcode) {
        /**
         * 重新计时
         */
        inactivityTimer.onActivity();
        /**
         * 将结果绘制到view中
         */
        viewfinderView.drawResultBitmap(barcode);
        /**
         * 播放jeep声音
         */
        playBeepSoundAndVibrate();
        /**
         * 显示解码字符串
         */
        if ("QR_CODE".equals(obj.getBarcodeFormat().toString())) {//二维码
            try {
                Intent intent = new Intent(CaptureActivity.this, GreenHouseGateBindActivity.class);
                // 设置要回传的数据
                intent.putExtra("result", obj.getText());
                // 设置回传码和Intent
                setResult(RESULT_OK, intent);
                finish();
            } catch (Exception e) {
                drawViewfinder();
                clones();
                hasSurface = true;
                init();
                ToastUtils.makeTextShort("抱歉，路径打不开~！");
                e.printStackTrace();
            }
        } else if ("EAN_13".equals(obj.getBarcodeFormat().toString())) {//条形码
            try {
                boolean b = obj.getText().startsWith("YBF");//判断字符串是否以YBF开头（规则）
                if (b) {
                    Intent intent = new Intent(CaptureActivity.this, GreenHouseGateBindActivity.class);
                    // 设置要回传的数据
                    intent.putExtra("result", obj.getText());
                    // 设置回传码和Intent
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    drawViewfinder();
                    clones();
                    hasSurface = true;
                    init();
                    ToastUtils.makeTextShort("您扫描的不是网关条形码~！");
                }
            } catch (Exception e) {
                drawViewfinder();
                clones();
                hasSurface = true;
                init();
                ToastUtils.makeTextShort("抱歉，路径打不开~！");
                e.printStackTrace();
            }
        }

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    /**
     * 返回按钮的点击事件 public
     */
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.qr_code_tv://设置
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 震动时间
     */
    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        /**
         * 播放声音
         */
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        /**
         * 震动
         */
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
}