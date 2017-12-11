package com.app.itserv.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.app.itserv.MineApplication;
import com.app.itserv.fragments.FarmingFragment;
import com.app.itserv.fragments.GHMonitoringFragment;
import com.app.itserv.fragments.HomePage;
import com.app.itserv.fragments.MineFragment;
import com.app.itserv.fragments.MonitoringFragment;
import com.app.itserv.fragments.NewFrament;
import com.app.itserv.jparser.PhoneListBean;
import com.google.gson.Gson;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.QuitDialogUtil;
import com.itserv.shed.R;
import com.yycloud.app.AlarmEventManager;
import com.yycloud.app.utils.TAPreferenceUtil;
import com.yycloud.app.utils.WAPI;
import com.yycloud.core.beans.AlarmIgnoreMSG;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/***
 * 主菜单界面
 *
 * @author Administrator
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tab_content)
    FrameLayout tabContent;
    @BindViews({R.id.radio_home, R.id.radio_monitor, R.id.radio_information, R.id.radio_account})
    List<TextView> radioButtons;
    private int position;
//    private HomePage homepage;
    private GHMonitoringFragment monitorFragment;
    private FarmingFragment farmingFragment;
    private NewFrament newFrament;
    private MineFragment mineFragment;
    public static Socket socket;
    private AlertDialog dialog;
    private AlertDialog dialogErr;
    private static boolean mErrdevWerning = true;
    private int notifyId = 100;


    @Override
    protected int layoutViewId() {
        return R.layout.home_lay;
    }

    @Override
    protected void init() {
        super.init();
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        AlarmEventManager.getInstance().init(null);
        radioButtons.get(0).setOnClickListener(this);
        radioButtons.get(1).setOnClickListener(this);
        radioButtons.get(2).setOnClickListener(this);
        radioButtons.get(3).setOnClickListener(this);
        setChioceItem(position);
        initNotify();
        initSocket();
        phoneNumberVerification();

    }

    private void initSocket() {
        final TAPreferenceUtil preferenceUtil = TAPreferenceUtil.getInstance(this);
        try {
            socket = IO.socket(WAPI.WAPI_HTTP_IO);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {// 与服务器创建连
            @Override
            public void call(Object... args) {
                JSONObject object = null;
                try {
                    String user = PreferencesUtils.getString(HomeActivity.this, "userName");
                    object = new JSONObject().accumulate("username",
                            user);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                socket.emit("login", object);
                showCzNotify("", "正在实时监控");
                Log.v("Socket", "----------连接成功----------");
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {// 连接断开了

            @Override
            public void call(Object... args) {

                Log.v("Socket", "==========断开连接===========");
                showCzNotify("", "app处于后台时间过长啦!请打开app查看");
            }

        }).on("push_to_" + PreferencesUtils.getString(HomeActivity.this, "userName"),// 监听根据返回的数据更新界面
                new Emitter.Listener() {

                    @Override
                    public void call(Object... args) {
                        JSONObject jsonObject = (JSONObject) args[0];
                        Gson gson = new Gson();

                        AlarmIgnoreMSG fromJson = gson.fromJson(
                                jsonObject.toString(), AlarmIgnoreMSG.class);
                        String event_level = fromJson.getData()
                                .getEvent_level();
                        if ("alarm-level-y".equals(event_level)) {
                            long[] vibrate = {0, 0, 0, 0};
                            showIntentActivityNotify("黄色预警", fromJson.getData()
                                            .getDetail(), 200,
                                    Notification.DEFAULT_LIGHTS, vibrate);
                        } else if ("alarm-level-o".equals(event_level)) {
                            long[] vibrate = {0, 2000, 1000, 3000};
                            showIntentActivityNotify("橙色预警", fromJson.getData()
                                            .getDetail(), 300,
                                    Notification.DEFAULT_VIBRATE, vibrate);
                        } else if ("alarm-level-r".equals(event_level)) {
                            long[] vibrate = {0, 2000, 1000, 3000};
                            showIntentActivityNotify("红色预警", fromJson.getData()// alarm-level-r
                                            .getDetail(), 400,
                                    Notification.DEFAULT_ALL, vibrate);
                        } else if ("device_owner_alarm".equals(fromJson.getData()
                                .getEvent_name())) {


                            if (mErrdevWerning) {
                                // 判断当前窗体的是不是dialog 是就break
                                if (dialog != null && dialog.isShowing()) {
                                    return;
                                }

                                if (dialogErr == null) {
                                    showDialogErrView(fromJson);
                                } else if (!dialogErr.isShowing()) {
                                    showDialogErrView(fromJson);
                                }


                            }

                            //                }

                        } else {
                            long[] vibrate = {0, 0, 0, 0};
                            showIntentActivityNotify("通知", fromJson.getData()
                                            .getDetail(), 500,
                                    Notification.DEFAULT_LIGHTS, vibrate);
                        }

                    }
                });

        // 开始连接

        socket.connect();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        setChioceItem(position);
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //记录当前的position
        outState.putInt("position", position);
    }

    public void setChioceItem(int index) {
        this.position = index;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragments(ft);
        ButterKnife.apply(radioButtons, TABSPEC, radioButtons.get(index));
        switch (index) {
            case 0:
                if (null == monitorFragment) {
                    monitorFragment = new GHMonitoringFragment();
                    ft.add(R.id.tab_content, monitorFragment);
                } else {
                    ft.show(monitorFragment);
                }
                break;
            case 1:
                // 农事
                if (null == farmingFragment) {
                    farmingFragment = new FarmingFragment();
                    ft.add(R.id.tab_content, farmingFragment);
                } else {
                    ft.show(farmingFragment);
                }
                break;
            case 2:
                // 资讯
                if (null == newFrament) {
                    newFrament = new NewFrament();
                    ft.add(R.id.tab_content, newFrament);
                } else {
                    ft.show(newFrament);
                }
                break;
            case 3:
                // 我的
                if (null == mineFragment) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.tab_content, mineFragment);
                } else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction ft) {
        if (null != monitorFragment)
            ft.hide(monitorFragment);
        if (null != farmingFragment)
            ft.hide(farmingFragment);
        if (null != newFrament)
            ft.hide(newFrament);
        if (null != mineFragment)
            ft.hide(mineFragment);
    }

    //控制normal 状态的当前View 隐藏，其它空间仍然为显示
    static final ButterKnife.Setter<TextView, TextView> TABSPEC = new ButterKnife.Setter<TextView, TextView>() {
        @Override
        public void set(TextView view, TextView value, int index) {
            if (view.getId() == value.getId()) {
                view.setSelected(true);
            } else {
                view.setSelected(false);
            }
        }
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
        if ((paramKeyEvent.getAction() == 0)
                && (paramKeyEvent.getKeyCode() == 4)) {
            exitApp();
        }

        return super.dispatchKeyEvent(paramKeyEvent);
    }

    private void exitApp() {
        QuitDialogUtil.getInstance(HomeActivity.this).showQuitDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                Log.e("reload", "reload");
                break;
            case 2:
                Log.e("cancel", "cancel");
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_home:
                setChioceItem(0);
                break;
            case R.id.radio_monitor:
                setChioceItem(1);
                break;
            case R.id.radio_information:
                setChioceItem(2);
                break;
            case R.id.radio_account:
                setChioceItem(3);
                break;
        }
    }


    /**
     * 初始化通知栏
     */
    private void initNotify() {
        MineApplication.mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        MineApplication.mBuilder = new NotificationCompat.Builder(this);
        MineApplication.mBuilder
                .setContentTitle("标题")
                .setContentText("内容")
                .setContentIntent(
                        MineApplication
                                .getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
                // .setNumber(1)// 显示数量
                .setTicker("正在监控")// 通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                .setAutoCancel(true)// 设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)// ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                // .setDefaults(Notification.DEFAULT_VIBRATE)//
                // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                // Notification.DEFAULT_ALL Notification.DEFAULT_SOUND 添加声音 //
                // requires VIBRATE permission
                .setSmallIcon(R.drawable.icon64);
    }

    /**
     * 显示常驻通知栏
     */
    public void showCzNotify(String title, String text) {
        // Notification mNotification = new
        // Notification();//为了兼容问题，不用该方法，所以都采用BUILD方式建立
        // Notification mNotification = new
        // Notification.Builder(this).getNotification();//这种方式已经过时
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this);
        // //PendingIntent 跳转动作
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                getIntent(), 0);
        mBuilder.setSmallIcon(R.drawable.icon64).setTicker("正在监控")
                .setContentTitle("云洋e农").setContentText(text)
                .setContentIntent(pendingIntent);
        Notification mNotification = mBuilder.build();
        // 设置通知 消息 图标
        mNotification.icon = R.drawable.logo72;
        // 在通知栏上点击此通知后自动清除此通知
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;// FLAG_ONGOING_EVENT
        // 在顶部常驻，可以调用下面的清除方法去除
        // FLAG_AUTO_CANCEL
        // 点击和清理可以去调
        // 设置显示通知时的默认的发声、震动、Light效果
        // mNotification.defaults = Notification.DEFAULT_VIBRATE;
        // 设置发出消息的内容
        mNotification.tickerText = "正在监控";
        // 设置发出通知的时间
        mNotification.when = System.currentTimeMillis();
        // mNotification.flags =
        // Notification.FLAG_AUTO_CANCEL;//在通知栏上点击此通知后自动清除此通知
        // mNotification.setLatestEventInfo(this, "常驻测试",
        // "使用cancel()方法才可以把我去掉哦", null); //设置详细的信息 ,这个方法现在已经不用了
        MineApplication.mNotificationManager.notify(notifyId, mNotification);
    }

    /**
     * 显示通知栏点击跳转到指定Activity
     */
    public void showIntentActivityNotify(String title, String text,
                                         int notifyId, int defaults, long[] vibrate) {
        // 自定义振动方式
        // Notification.FLAG_ONGOING_EVENT --设置常驻
        // Flag;Notification.FLAG_AUTO_CANCEL 通知栏上点击此通知后自动清除此通知
        // notification.flags = Notification.FLAG_AUTO_CANCEL;
        // //在通知栏上点击此通知后自动清除此通知
        MineApplication.mBuilder
                .setAutoCancel(true)
                // 点击后让通知将消失
                .setContentTitle(title).setContentText(text).setTicker("通知")
                .setWhen(System.currentTimeMillis()).setVibrate(vibrate)
                .setDefaults(defaults);
        // 点击的意图ACTION是跳转到Intent
        Intent resultIntent = new Intent(this, MyEventActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // MineApplication.mBuilder.setFullScreenIntent(null, true);
        MineApplication.mBuilder.setContentIntent(pendingIntent);
        MineApplication.mNotificationManager.notify(notifyId,
                MineApplication.mBuilder.build());
    }

    private void showDialogErrView(AlarmIgnoreMSG alarmIgnore) {
        dialogErr = new AlertDialog.Builder(this).create();
        // 设置点击范围外 无效果
        dialogErr.setCanceledOnTouchOutside(false);
        dialogErr.show();

        View alarm_number_dialogErr = LayoutInflater.from(this)
                .inflate(R.layout.alarm_number_dialog, null);

        dialogErr
                .addContentView(
                        alarm_number_dialogErr,
                        new ViewGroup.LayoutParams(
                                (int) (this.getResources()
                                        .getDisplayMetrics().widthPixels * 0.9),
                                ViewGroup.LayoutParams.WRAP_CONTENT));

        Button btn_alarmnumber_ensurequit = (Button) alarm_number_dialogErr
                .findViewById(R.id.btn_alarmnumber_ensurequit);
        Button btn_alarmnumber_cancelquit = (Button) alarm_number_dialogErr
                .findViewById(R.id.btn_alarmnumber_cancelquit);
        TextView tv_vistion_text = (TextView) alarm_number_dialogErr
                .findViewById(R.id.tv_vistion_text);

        tv_vistion_text.setText(alarmIgnore.getData().getDetail());

        btn_alarmnumber_ensurequit
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        dialogErr.dismiss();
                        mErrdevWerning = false;
                    }
                });

        btn_alarmnumber_cancelquit
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        dialogErr.dismiss();
                        mErrdevWerning = true;
                    }
                });
    }

    private void phoneNumberVerification() {
        HttpManager.getInstance().gUserInfo(TAG, new HttpCallBack<PhoneListBean>() {

            @Override
            public void onSuccess(PhoneListBean date) {

                List<PhoneListBean.ContactsBean> contacts = date.getContacts();
                if (contacts.size() <= 0)
                    showPhoneDialog();
            }

            @Override
            public void onError(Throwable throwable) {
                //   ToastUtils.makeTextShort("网络不可用!");  //数据迁移未完成,待完成后可打开
            }
        });
    }

    private void showPhoneDialog() {
        dialog = new AlertDialog.Builder(HomeActivity.this).create();
        // 设置点击范围外 无效果
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        View alarm_number_dialog = LayoutInflater.from(HomeActivity.this).inflate(R.layout.alarm_number_dialog, null);

        dialog.addContentView(alarm_number_dialog, new ViewGroup.LayoutParams((int) (HomeActivity.this
                .getResources().getDisplayMetrics().widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT));
        Button btn_alarmnumber_ensurequit = (Button) alarm_number_dialog
                .findViewById(R.id.btn_alarmnumber_ensurequit);
        Button btn_alarmnumber_cancelquit = (Button) alarm_number_dialog
                .findViewById(R.id.btn_alarmnumber_cancelquit);
        btn_alarmnumber_ensurequit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                Intent intent2 = new Intent(HomeActivity.this, AlarmNumberActivity.class);
                HomeActivity.this.startActivity(intent2);

            }
        });

        btn_alarmnumber_cancelquit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(
                    View arg0) {
                dialog.dismiss();
            }
        });
    }
}
