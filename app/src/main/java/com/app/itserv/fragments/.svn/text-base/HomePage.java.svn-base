package com.app.itserv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.itserv.MineApplication;
import com.app.itserv.activity.AdvisoryproposalActivity;
import com.app.itserv.activity.ContactTitleActivity;
import com.app.itserv.activity.HomeActivity;
import com.app.itserv.activity.InternalMessageActivity;
import com.app.itserv.activity.LoginLogActivity;
import com.app.itserv.activity.MyEventActivity;
import com.app.itserv.activity.MyFarmingReportActivity;
import com.app.itserv.activity.MyFarmingTaskActivity;
import com.app.itserv.activity.MySysNoticeActivity;
import com.app.itserv.activity.ShareActivity;
import com.app.itserv.activity.WeatherActivity;
import com.app.itserv.activity.WebViewActivity;
import com.app.itserv.adapters.Bee_PageAdapter;
import com.app.itserv.jparser.JsonAdvertisingObject;
import com.app.itserv.jparser.JsonAdvertisingObject.ObjBean;
import com.app.itserv.jparser.JsonMySysNoticeObject;
import com.app.itserv.views.ChildViewPager;
import com.app.itserv.views.ChildViewPager.onSimpleClickListener;
import com.app.itserv.views.MarqueeText;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.EmptyUtils;
import com.itserv.app.util.LogUtils;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.viewpagerindicator.CirclePageIndicator;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @project name：yyshed
 * @type name：HomePage
 * @description：九宫格首页
 * @author：haoruigang
 * @date time：2017-6-9 上午9:33:20
 */
public class HomePage extends Fragment implements onSimpleClickListener,
        OnPageChangeListener, OnClickListener {
    public static final String TAG = Class.class.getClass().getName();
    protected static final int ADVER_ERROR = 0;

    /* 轮播图start */
    private ChildViewPager viewpager;
    private CirclePageIndicator indicator;
    // 滚动显示图片ImageView显示集合
    private ArrayList<View> bannerListView = new ArrayList<>();
    private Runnable mPagerAction;
    private float xDistance, yDistance;
    /**
     * 记录按下的X坐标
     **/
    private float mLastMotionX, mLastMotionY;
    private boolean isScrolled = false;
    private boolean mIsBeingDragged = true;
    private Bee_PageAdapter adapter;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private int mItem = 0;
    boolean isFrist = true;
    int mCurrentItem = 0;
    /* 轮播图end */

    /* 跑马灯start */
    private MarqueeText text;

    /* 联系客服start */
    private ImageView img_Contact;
    /* 联系客服end */

    private RelativeLayout rlNetworkShed;
    private LinearLayout llSysAlarm, llSysAnnoun, llInternalMsg, llAdvSug,
            llLoginlog, llOriginMag;

    private TextView tvWeather, tvInformation, tvNetworkShed, tvMyFarmingTask, tvMyFarming_report;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ADVER_ERROR:// 广告列表请求错误
                    TAUtils.toastMessage(getActivity(), String.valueOf(msg.obj));
                    break;
                default:
                    break;
            }
        }

    };
    private List<ObjBean> beanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.home_page, container, false);
        initView(mView);
        init();
        return mView;
    }

    /**
     * 初始化视图控件
     *
     * @param mView
     */
    private void initView(View mView) {
        /*************** 顶部广告部分开始 ****************/
        bannerListView = new ArrayList<View>();
        bannerListView.clear();
        ImageLoaderConfiguration config = MineApplication
                .getConfig(getActivity());
        imageLoader.init(config);
        viewpager = (ChildViewPager) mView.findViewById(R.id.banner_viewpager);
        viewpager.setOnSimpleClickListener(this);
        viewpager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                viewpager.getGestureDetector().onTouchEvent(event);
                final float x = event.getRawX();
                final float y = event.getRawY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xDistance = yDistance = 0f;
                        mLastMotionX = x;
                        mLastMotionY = y;
                    case MotionEvent.ACTION_MOVE:
                        final float xDiff = Math.abs(x - mLastMotionX);
                        final float yDiff = Math.abs(y - mLastMotionY);
                        xDistance += xDiff;
                        yDistance += yDiff;
                        /** 左右滑动避免和下拉刷新冲突 **/
                        if (xDistance > yDistance
                                || Math.abs(xDistance - yDistance) < 0.00001f) {
                            mIsBeingDragged = true;
                            mLastMotionX = x;
                            mLastMotionY = y;
                            v.getParent().requestDisallowInterceptTouchEvent(true);
                        } else {
                            mIsBeingDragged = false;
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        if (mIsBeingDragged) {
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        indicator = (CirclePageIndicator) mView.findViewById(R.id.indicator);
        adapter = new Bee_PageAdapter(bannerListView);
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
        indicator.setOnPageChangeListener(this);
        viewpager.setCurrentItem(0);
        /**
         * 轮播图自动循环滚动
         */
        mPagerAction = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stu
                if (mItem != 0) {

                    if (isFrist == true) {
                        mCurrentItem = 0;
                        isFrist = false;
                    } else {
                        if (mCurrentItem == bannerListView.size() - 1) {
                            mCurrentItem = 0;
                        } else {
                            mCurrentItem++;
                        }
                    }
                    viewpager.setCurrentItem(mCurrentItem);
                }
                viewpager.postDelayed(mPagerAction, 2500);
            }
        };
        viewpager.postDelayed(mPagerAction, 100);
        /*************** 广告设置部分结束 ****************/
        /* 跑马灯start */
        text = (MarqueeText) mView.findViewById(R.id.test);
        text.startScroll();
        /* 跑马灯end */
        /*
         * 九宫格start list_home = (PagerGridView)
		 * mView.findViewById(R.id.list_home); list_home.setAdapter(new
		 * HomeGridAdapter(getActivity(), names, ids));
		 * list_home.setOnItemClickListener(this);
		 * list_home.setHaveScrollbar(false); /* 九宫格end /* 联系客服start
		 */
        img_Contact = (ImageView) mView.findViewById(R.id.img_contact);
        img_Contact.setOnClickListener(this);
        /* 联系客服end */

        tvWeather = (TextView) mView.findViewById(R.id.tv_weather);//天气
        tvWeather.setOnClickListener(this);
        tvInformation = (TextView) mView
                .findViewById(R.id.tv_information);// 农业资讯
        tvInformation.setOnClickListener(this);
        tvNetworkShed = (TextView) mView
                .findViewById(R.id.tv_network_shed);// 棚联网
        tvNetworkShed.setOnClickListener(this);
        tvMyFarmingTask = (TextView) mView.findViewById(R.id.my_farming_task);// 农事任务
        tvMyFarmingTask.setOnClickListener(this);
        tvMyFarming_report = (TextView) mView
                .findViewById(R.id.my_farming_report);// 农事填报
        tvMyFarming_report.setOnClickListener(this);
        llSysAlarm = (LinearLayout) mView.findViewById(R.id.ll_sys_alarm);// 系统告警
        llSysAlarm.setOnClickListener(this);
        llSysAnnoun = (LinearLayout) mView.findViewById(R.id.ll_sys_announ);// 系统公告
        llSysAnnoun.setOnClickListener(this);
        llInternalMsg = (LinearLayout) mView.findViewById(R.id.ll_internal_msg);// 内部消息
        llInternalMsg.setOnClickListener(this);
        llAdvSug = (LinearLayout) mView.findViewById(R.id.ll_adv_sug);// 咨询建议
        llAdvSug.setOnClickListener(this);
        llLoginlog = (LinearLayout) mView.findViewById(R.id.ll_login_log);// 登录日志
        llLoginlog.setOnClickListener(this);
        llOriginMag = (LinearLayout) mView.findViewById(R.id.ll_origin_mag);// 溯源管理
        llOriginMag.setOnClickListener(this);
    }

    private void init() {
        // TODO Auto-generated method stub
        AdvertisingRequest();// 广告列表请求线程
        setapiNoticeControllerList();
    }

    //公告
    public void setapiNoticeControllerList() {
        String token = PreferencesUtils.getString(getActivity(), "token");// token
        String currTenantId = PreferencesUtils.getString(getActivity(),
                "tenantId");// 商户id
        HttpManager.getInstance().apiunReadNoticeControllerList(TAG, token, currTenantId, new HttpCallBack<JsonMySysNoticeObject>() {
            @Override
            public void onError(Throwable throwable) {
                text.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(JsonMySysNoticeObject date) {
                if (date != null && date.isSuccess() && null != date.getObj() && date.getObj().size() > 0) {
                    String textString = "";
                    for (int i = 0; i < date.getObj().size(); i++) {
                        String strtime = date.getObj().get(i).getCreateTime();// 时间
                        strtime = strtime.replace(".0", "");
                        textString += (strtime + "   " + date.getObj().get(i).getNoticeTitle() + "                     ");
                    }
                    text.setText(textString);
                    text.setVisibility(View.VISIBLE);
                } else
                    text.setVisibility(View.GONE);
            }
        });
    }

    /**
     * @project name：yyshed
     * @type name：AdvertisingRequest
     * @description：广告列表请求线程
     * @author：gang
     * @date time：2017-6-9 上午9:44:32
     */
    public void AdvertisingRequest() {
        String currTenantId = PreferencesUtils.getString(getActivity(),
                "tenantId");// 商户id
        String token = PreferencesUtils.getString(getActivity(),
                "token");// token
        String clientCode = "cpms";// 平台编码：
        // 1.cpms 云管;2.csss 商户;3.mobileapp
        // 手机APP;
        // 4.gwapp网管APP
        HttpManager.getInstance().advertising(TAG, currTenantId, token, clientCode, new HttpCallBack<JsonAdvertisingObject>(getActivity(), false) {
            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }

            @Override
            public void onSuccess(JsonAdvertisingObject date) {
                Log.i(TAG, date.toString());
                if (date != null) {
                    if (date.isSuccess()) {// 成功
                        // 对象列表
                        beanList = date.getObj();
                        if (null != beanList)
                            initAllItems();
                    }
                }
            }
        });
    }

    private void initAllItems() {
        // 初始化Viewpager的所有item
        for (int i = 0; i < beanList.size(); i++) {
            bannerListView.add(initPagerItem(i, beanList.get(i).getAttachment1()));
        }
        mItem = bannerListView.size();// 轮播个数
        adapter.notifyDataSetChanged();
        indicator.notifyDataSetChanged();
    }

    private View initPagerItem(int index, String img) {
        ImageView imagView1 = (ImageView) getActivity().getLayoutInflater()
                .inflate(R.layout.b0_index_banner_cell, null);
        if (EmptyUtils.isEmpty(img))
            imagView1.setImageResource(index % 2 == 0 ? R.drawable.banner2 : R.drawable.banner3);
        else
            imageLoader.displayImage(WapiUtil.WAPI_URL_S + img, imagView1, MineApplication.default_img);
        return imagView1;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 轮播图片点击事件
     */
    @Override
    public void setOnSimpleClickListenr(int position) {
        // TODO Auto-generated method stub
        LogUtils.e("setOnSimpleClickListenr", "position:" + position);
        if (EmptyUtils.isEmpty(beanList.get(position).getOpenurl()))
            return;
        Intent intent = new Intent();
        intent.setClass(getActivity(), WebViewActivity.class);
        intent.putExtra("url", beanList.get(position).getOpenurl());
        getActivity().startActivity(intent);
    }

    @Override
    public void onPageScrollStateChanged(int status) {
        // TODO Auto-generated method stub
        switch (status) {
            case 1:// 手势滑动
                isScrolled = false;
                break;
            case 2:// 界面切换
                isScrolled = true;
                break;
            case 0:// 滑动结束

                // 当前为最后一张，此时从右向左滑，则切换到第一张
                if (viewpager.getCurrentItem() == viewpager.getAdapter().getCount() - 1
                        && !isScrolled) {
                    indicator.setCurrentItem(0);
                }
                // 当前为第一张，此时从左向右滑，则切换到最后一张
                else if (viewpager.getCurrentItem() == 0 && !isScrolled) {
                    indicator.setCurrentItem(viewpager.getAdapter().getCount() - 1);
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        mCurrentItem = arg0;
    }

    /**
     * 九宫格点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_contact:// 客服
                Log.i("HomePage", "客服");
                startActivity(new Intent(getActivity(), ContactTitleActivity.class));
                break;
            case R.id.tv_weather:// 天气跳转网页
                Intent it = new Intent(getActivity(), WeatherActivity.class).putExtra("WebURL", "weather");
                startActivity(it);
                break;
            case R.id.tv_information:// 农业资讯
                Log.i("HomePage", "农业资讯");
                ((HomeActivity) getActivity()).setChioceItem(2);
                break;
            case R.id.tv_network_shed:// 棚联网跳转网页
                Log.i("HomePage", "棚联网");
//                if (PackageUtils.isApkInstall(getActivity(), "com.tencent.mm")) {
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
//                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
//                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    intent.setData(Uri.parse("weixin://dl/business/?ticket=https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI5NjU5NTU4Nw==&scene=124&#wechat_redirect#wechat_redirect"));
//                                              weixin://dl/business/?ticket=ta9314419d71afd613b4eb187f35c8a26#wechat_redirect
//                    intent.setComponent(cmp);
//                    startActivity(intent);
//                } else {
//                    ToastUtils.makeTextShort("未安装微信客户端");
//                }
//                Intent network = new Intent(Intent.ACTION_VIEW);
//                network.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                network.setData(Uri.parse("weixin://dl/business/?ticket=ta9314419d71afd613b4eb187f35c8a26#wechat_redirect"));
//                startActivity(network);
                // 公众号二维码  周作威  2017/9/28 16:40
                Intent intent3 = new Intent(getActivity(), ShareActivity.class);
                intent3.putExtra("type", 2);
                startActivity(intent3);
                break;
            case R.id.my_farming_task:// 农事任务
                Log.i("HomePage", "农事任务");
                startActivity(new Intent(getActivity(), MyFarmingTaskActivity.class));
                break;
            case R.id.my_farming_report:// 农事填报
                Log.i("HomePage", "农事填报");
                //当日任务对象
                String todayTaskId = PreferencesUtils.getString(getActivity(), "todayTaskId");
                if (todayTaskId == null) {
                    ToastUtils.makeTextShort("未领用任务不能填报!");
                } else {
                    startActivity(new Intent(getActivity(), MyFarmingReportActivity.class).putExtra("todayTaskId", todayTaskId));
                }
                break;
            case R.id.ll_sys_alarm:// 系统告警
                Log.i("HomePage", "系统告警");
                /*
                 * startActivity(new Intent(getActivity(), MyEventActivity.class)
                 * .putExtra("SysAlarmNotice", "sysalarm"));
                 */
                startActivity(new Intent(getActivity(), MyEventActivity.class));
                break;
            case R.id.ll_sys_announ:// 系统公告
                startActivity(new Intent(getActivity(), MySysNoticeActivity.class));
                break;
            case R.id.ll_internal_msg:// 内部消息
                startActivity(new Intent(getActivity(),
                        InternalMessageActivity.class));
                break;
            case R.id.ll_adv_sug:// 咨询建议
                startActivity(new Intent(getActivity(),
                        AdvisoryproposalActivity.class));
                break;
            case R.id.ll_login_log:// 登录日志
                startActivity(new Intent(getActivity(), LoginLogActivity.class));
                break;
            case R.id.ll_origin_mag:// 溯源管理
                Log.i("HomePage", "溯源管理");
                ToastUtils.makeTextShort("溯源管理功能未开放");
                break;
            default:
                break;
        }
    }
}
