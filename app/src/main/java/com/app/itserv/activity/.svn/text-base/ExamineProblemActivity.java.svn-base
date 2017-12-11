package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.itserv.adapters.ExamineProbAdapter;
import com.app.itserv.jparser.JsonAdvisorySuperadditionObject;
import com.app.itserv.jparser.JsonExamineProblemObject;
import com.app.itserv.jparser.JsonExamineProblemObject.AttributesBean;
import com.app.itserv.jparser.JsonExamineProblemObject.ObjBean;
import com.app.itserv.jparser.JsonExamineProblemObject.ObjBean.UserAdviceBean;
import com.app.itserv.jparser.JsonExamineProblemObject.ObjBean.UserAdviceReadReplyListBean;
import com.app.itserv.views.ListViewForScrollView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 问题查看
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyshed
 * @ClassName: ExamineProblemActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-6-29 下午4:14:57
 */
public class ExamineProblemActivity extends Activity implements OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;//返回
    @BindView(R.id.tv_problem_state)
    TextView tvProblemState;//答复状态
    @BindView(R.id.ll_problem)
    LinearLayout llProblem;//根据状态显示背景
    @BindView(R.id.problem_class)
    TextView problemClass;// 问题分类
    @BindView(R.id.tv_question_time)
    TextView tvQuestionTime;//提问时间
    @BindView(R.id.task_name)
    TextView taskName;// 问题标题
    @BindView(R.id.task_describe)
    EditText taskDescribe;// 详情
    @BindView(R.id.annex_one)
    TextView annexOne;//附件1
    @BindView(R.id.annex_two)
    TextView annexTwo;//附件2
    @BindView(R.id.annex_three)
    TextView annexThree;//附件3
    @BindView(R.id.examine_pro_lv)
    ListViewForScrollView examineProLv;//listview  答复消息
    @BindView(R.id.examine_pro_ll_notarize)
    LinearLayout examineProLlNotarize;//确认信息的布局
    @BindView(R.id.tv_replay_message)
    EditText tvReplayMessage;//您对答复的意见内容
    @BindView(R.id.examine_pro_ll_reply_message)
    LinearLayout examineProLlReplyMessage;//您对答复的意见信息的布局
    @BindView(R.id.btn_add_questions)
    Button btnAddQuestions;//追加提问
    @BindView(R.id.btn_close_problem)
    Button btnCloseProblem;//关闭问题
    @BindView(R.id.examine_pro_ll_supply)
    LinearLayout examineProLlSupply;//追加提问和关闭问题的布局

    private Context mContext;

    private static final String TAG = "ExamineProblemActivity";
    protected static final int PROPOSALEXA_ERROR = 1;
    protected static final int PROPOSALEXA_SUCCESS = 2;
    protected static final int PROPOSALEXA_VALUES = 3;
    protected static final int Superaddition_ERROR = 4;//追加提问错误
    protected static final int Superaddition_SUCCESS = 5;//追加提问成功
    protected static final int AdviceCloseInfo_ERROR = 6;//关闭问题失败
    protected static final int AdviceCloseInfo_SUCCESS = 7;//关闭问题成功

    private String replaymessage;
    private String adviceId;// 投诉建议id
    private ObjBean objbean;
    //回复集合
    private List<UserAdviceReadReplyListBean> userAdviceReadReplyListBeans = new ArrayList<>();
    //咨询表对象
    private UserAdviceBean userAdviceBean;//您对答复的意见内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examine_prob_lay);
        ButterKnife.bind(this);
        adviceId = getIntent().getExtras().getString("adviceId");
        mContext = this;
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        new Thread(new ProblemRequest()).start();
    }

    @OnClick({R.id.img_back, R.id.btn_add_questions, R.id.btn_close_problem})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_add_questions://追加提问
                replaymessage = tvReplayMessage.getText().toString();//追加内容
                if (TextUtils.isEmpty(replaymessage)) {
                    TAUtils.toastMessage((Activity) mContext, "内容不能为空");
                    return;
                }
                new Thread(new Superaddition()).start();
                break;
            case R.id.btn_close_problem://关闭问题
                new Thread(new AdviceCloseInfo()).start();
                break;
            default:
                break;
        }
    }


    /**
     * 问题详情报文json请求
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: ProblemAction
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-1 下午6:00:24
     */
    class ProblemAction extends Thread {

        private String proposalExajson;

        public ProblemAction(String proposalExa_json) {
            // TODO Auto-generated constructor stub
            this.proposalExajson = proposalExa_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(proposalExajson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonExamineProblemObject problemObj = gson.fromJson(proposalExajson, JsonExamineProblemObject.class);
                if (!problemObj.equals("") && problemObj != null) {

                    if (problemObj.isSuccess()) {// 成功
                        AttributesBean attributesbean = problemObj.getAttributes();
                        attributesbean.getCurrUserId();
                        attributesbean.getCurrTenantId();

                        objbean = problemObj.getObj();
                        //添加到回复集合
                        userAdviceReadReplyListBeans = objbean.getUserAdviceReadReplyList();
                        //添加咨询对象
                        userAdviceBean = objbean.getUserAdvice();

                        Message msg = Message.obtain();
                        msg.what = PROPOSALEXA_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                problemObj.getMsg());
                }
            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }

        }
    }

    //-------------------------------------------------
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PROPOSALEXA_ERROR:// 咨询建议问题详情请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case PROPOSALEXA_SUCCESS:
                    String proposalExa_json = msg.obj.toString();
                    new Thread(new ProblemAction(proposalExa_json)).start();// 投诉建议问题详情报文解析线程
                    break;
                case PROPOSALEXA_VALUES:
                    //设置适配器
                    ExamineProbAdapter adapter = new ExamineProbAdapter(userAdviceReadReplyListBeans, mContext);
                    examineProLv.setAdapter(adapter);

                    if (!userAdviceBean.getAdviceSendCategory().isEmpty()) {
                        if (userAdviceBean.getAdviceSendCategory().equals("NOTIFY")) {//告知
                            problemClass.setText("告知");// 问题分类
                        } else if (userAdviceBean.getAdviceSendCategory().equals("CONSULT")) {//咨询
                            problemClass.setText("咨询");// 问题分类
                        } else if (userAdviceBean.getAdviceSendCategory().equals("ADVICE")) {//建议
                            problemClass.setText("建议");// 问题分类
                        } else if (userAdviceBean.getAdviceSendCategory().equals("COMPLAIN")) {//投诉
                            problemClass.setText("投诉");// 问题分类
                        } else if (userAdviceBean.getAdviceSendCategory().equals("OTHER")) {//其它
                            problemClass.setText("其它");// 问题分类
                        }
                    }
                    //时间格式转换
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long lquestiontime = userAdviceBean.getCreateDate();//提问时间
                    if (lquestiontime != 0) {
                        Date datequestiontime = new Date(lquestiontime); // 根据long类型的毫秒数生命一个date类型的时间
                        String Squestiontime = simpleDateFormat.format(datequestiontime); // 把date类型的时间转换为string
                        tvQuestionTime.setText(Squestiontime);//提问时间
                    } else {
                        tvQuestionTime.setText("未知");//提问时间
                    }
                    //答复状态
                    String Sproblemstate = userAdviceBean.getAdviceReplyStatus();
                    if (!TextUtils.isEmpty(Sproblemstate)) {
                        if (Sproblemstate.equals("WAITINGREPLY")) {//未回复
                            tvProblemState.setText("未答复");
                            examineProLlNotarize.setVisibility(View.GONE);//确认信息的布局
                            examineProLlReplyMessage.setVisibility(View.GONE);//您对答复的意见信息的布局
                            examineProLlSupply.setVisibility(View.GONE);//追加提问和关闭问题的布局
                            llProblem.setBackgroundColor(getResources().getColor(R.color.deepskyblue));
                        } else if (Sproblemstate.equals("REPLYED")) {//已回复
                            tvProblemState.setText("已答复");
                            examineProLlNotarize.setVisibility(View.VISIBLE);//确认信息的布局
                            examineProLlReplyMessage.setVisibility(View.VISIBLE);//您对答复的意见信息的布局
                            examineProLlSupply.setVisibility(View.VISIBLE);//追加提问和关闭问题的布局
                            llProblem.setBackgroundColor(getResources().getColor(R.color.darkseagreen));
                        } else if (Sproblemstate.equals("CLOSED")) {//已关闭
                            tvProblemState.setText("已关闭");
                            examineProLlNotarize.setVisibility(View.GONE);//确认信息的布局
                            examineProLlReplyMessage.setVisibility(View.GONE);//您对答复的意见信息的布局
                            examineProLlSupply.setVisibility(View.GONE);//追加提问和关闭问题的布局
                            llProblem.setBackgroundColor(getResources().getColor(R.color.darkgoldenrod));
                        }
                    }

                    taskName.setText(TextUtils.isEmpty(userAdviceBean.getNoticeTitle()) ? "未知标题" : userAdviceBean.getNoticeTitle());// 问题标题
                    taskDescribe.setText(TextUtils.isEmpty(userAdviceBean.getNoticeContent()) ? "未知内容" : userAdviceBean.getNoticeContent());// 问题内容
                    break;
                case Superaddition_ERROR://追加提问失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case Superaddition_SUCCESS://追加提问成功
                    String superaddition_json = msg.obj.toString();
                    new Thread(new SuperadditionSuccess(superaddition_json)).start();// 投诉建议问题追加报文解析线程
                    break;
                case AdviceCloseInfo_ERROR://关闭失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case AdviceCloseInfo_SUCCESS://关闭成功
                    String adviceCloseInfo_json = msg.obj.toString();
                    new Thread(new AdviceCloseSuccess(adviceCloseInfo_json)).start();// 投诉建议问题关闭报文解析线程
                    break;

                default:
                    break;
            }
        }


    };


//----------------------查看详情的   请求----------------------------------

    /**
     * 问题详情请求线程
     *
     * @author haoruigang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: ProblemRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-1 上午9:36:54
     */
    class ProblemRequest extends Thread {

        private static final String TAG = "ProblemRequest";

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {

                Looper.prepare();

                String token = PreferencesUtils.getString(mContext, "token");// 用户token
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id

                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userAdviceId", adviceId);

                Log.i(TAG, TAG + "问题详情请求");

                WapiUtilEx.doProposalExa(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = PROPOSALEXA_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = PROPOSALEXA_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);
                    }
                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }

        }
    }

//-------------------追加提问请求----------------------------------------------	

    /**
     * 追加提问请求
     *
     * @author yiqiang
     */
    class Superaddition extends Thread {
        private static final String TAG = "Superaddition";

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
                String token = PreferencesUtils.getString(mContext, "token");// 用户id
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id

                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userAdviceId", adviceId);//咨询ID
                map.put("noticeContent1", replaymessage);//追加内容
                Log.i(TAG, TAG + "追加提问请求");
                WapiUtilEx.replyAdvisory(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = Superaddition_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = Superaddition_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);
                    }
                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }

    /**
     * 追加提问报文json请求
     *
     * @author 常益强
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: SuperadditionSuccess
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12下午6:00:24
     */
    class SuperadditionSuccess extends Thread {

        private String superadditionjson;

        public SuperadditionSuccess(String superaddition_json) {
            // TODO Auto-generated constructor stub
            this.superadditionjson = superaddition_json;
        }

        @Override
        public void run() {//JsonAdvisorySuperadditionObject
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(superadditionjson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonAdvisorySuperadditionObject jsonAdvisorySuperaddition = gson.fromJson(superadditionjson, JsonAdvisorySuperadditionObject.class);
                if (!jsonAdvisorySuperaddition.equals("") && jsonAdvisorySuperaddition != null) {
                    if (jsonAdvisorySuperaddition.isSuccess()) {// 成功
                        Message msg = Message.obtain();
                        msg.what = PROPOSALEXA_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        TAUtils.toastMessage((Activity) mContext, jsonAdvisorySuperaddition.getMsg());
                }
            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }

        }
    }


//--------------------关闭问题--------------------------------------------	


    /**
     * 关闭问题请求
     *
     * @author yiqiang
     */
    class AdviceCloseInfo extends Thread {
        private static final String TAG = "AdviceCloseInfo";

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
                String token = PreferencesUtils.getString(mContext, "token");// 用户id
                String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id

                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userAdviceId", adviceId);//咨询ID
                Log.i(TAG, TAG + "关闭问题请求");
                WapiUtilEx.closeAdvisory(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = AdviceCloseInfo_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = AdviceCloseInfo_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);
                    }
                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }
        }
    }
//----------------解析关闭成功的json串---------------------		

    /**
     * 追加提问报文json请求
     *
     * @author 常益强
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: SuperadditionSuccess
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-12下午6:00:24
     */
    class AdviceCloseSuccess extends Thread {

        private String adviceClosejson;

        public AdviceCloseSuccess(String adviceClosejson_json) {
            // TODO Auto-generated constructor stub
            this.adviceClosejson = adviceClosejson_json;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                Looper.prepare();

                if (TextUtils.isEmpty(adviceClosejson)) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }

                Gson gson = new Gson();
                JsonAdvisorySuperadditionObject jsonAdvisorySuperaddition = gson.fromJson(adviceClosejson, JsonAdvisorySuperadditionObject.class);
                if (!jsonAdvisorySuperaddition.equals("") && jsonAdvisorySuperaddition != null) {
                    if (jsonAdvisorySuperaddition.isSuccess()) {// 成功
                        Log.i(TAG, jsonAdvisorySuperaddition.getMsg() + "");
                        Message msg = Message.obtain();
                        msg.what = PROPOSALEXA_VALUES;
                        mHandler.sendMessage(msg);
                    } else
                        TAUtils.toastMessage((Activity) mContext, jsonAdvisorySuperaddition.getMsg());
                }
            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }

        }
    }

}
