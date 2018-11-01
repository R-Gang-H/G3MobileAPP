package com.app.itserv.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.app.commons.ToUIEvent;
import com.app.itserv.activity.BaseViewActivity;
import com.app.itserv.activity.GreenHouseSortActivity;
import com.app.itserv.jparser.JsonBaseDelObject;
import com.app.itserv.jparser.JsonBaseManagerObject.ObjBean;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;

import java.io.Serializable;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 基地管理适配器
 *
 * @author Administrator
 */
public class BaseManagerAdapter extends BaseAdapter {

    private Context mContext;

    private static final String TAG = "BaseManagerAdapter";

    protected static final int BASEDEL_ERROR = 1;
    protected static final int BASEDEL_SUCCESS = 2;
    protected static final int BASEDEL_VALUES = 3;

    private List<ObjBean> baseObjList;
    private List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> userStatukList;
    public Object baseDelObj;
    private ViewHolder vHolder;

    /* 弹出dialog框start */
    private AlertDialog dialog;
    private Button quitEnsure;
    private Button quitCancel;

    // private String loginBase = "山东寿光联通合作示范基地";

    /* 弹出dialog框end */

    public BaseManagerAdapter(
            Context mContext,
            List<ObjBean> baseObjList,
            List<com.app.itserv.jparser.JsonDataDictionaryObject.ObjBean> userStatukList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.baseObjList = baseObjList;
        this.userStatukList = userStatukList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return baseObjList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return baseObjList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.base_manager_items,
                    null);
            vHolder = new ViewHolder();
            vHolder.tvBasename = (TextView) convertView
                    .findViewById(R.id.basename);// 基地名称
            vHolder.tvBasecode = (TextView) convertView
                    .findViewById(R.id.basecode);// 基地编号
            vHolder.tvActivaDate = (TextView) convertView
                    .findViewById(R.id.tv_activa_date);// 启动时间
            vHolder.tvPlanArea = (TextView) convertView
                    .findViewById(R.id.tv_plan_area);// 总面积
            vHolder.tvUseState = (TextView) convertView
                    .findViewById(R.id.tv_use_state);// 使用状态
            vHolder.tvExamine = (TextView) convertView
                    .findViewById(R.id.tv_examine);// 查看
            vHolder.tvDel = (TextView) convertView.findViewById(R.id.tv_del);// 删除
            vHolder.tvSort = (TextView) convertView.findViewById(R.id.tv_sort);// 大棚排序
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        ObjBean baseObj = (ObjBean) getItem(position);
        String baseId = TextUtils.isEmpty(baseObj.getId()) ? "" : baseObj
                .getId();
        String baseName = TextUtils.isEmpty(baseObj.getBaseFullname()) ? ""
                : baseObj.getBaseFullname();// 基地名称
        String baseCode = TextUtils.isEmpty(baseObj.getBaseCode()) ? ""
                : baseObj.getBaseCode();// 基地编号
        String openDate = TextUtils.isEmpty(String.valueOf(baseObj
                .getOpenDateOpen())) ? "" : String.valueOf(baseObj
                .getOpenDateOpen());// 启用日期
        String planArea = TextUtils.isEmpty(String.valueOf(baseObj
                .getCoveredArea())) ? "" : String.valueOf(baseObj
                .getCoveredArea());// 总面积
        String useState = TextUtils
                .isEmpty(String.valueOf(baseObj.getStatus())) ? "" : String
                .valueOf(baseObj.getStatus());// 使用状态

        try {
            vHolder.tvBasename.setText(baseName.toString().trim());
            vHolder.tvBasecode.setText(baseCode.toString().trim());
            vHolder.tvActivaDate.setText(DateLocalUtil.getDate(openDate
                    .toString().trim()));
            vHolder.tvPlanArea.setText(planArea.toString().trim() + "(平米)");

            for (int i = 0; i < userStatukList.size(); i++) {
                String userStatuId = userStatukList.get(i).getTypecode();
                if (useState.equals(userStatuId)) {
                    String userStatuName = userStatukList.get(i).getTypename();
                    vHolder.tvUseState.setText(userStatuName);// 使用状态
                }
            }
            vHolder.tvExamine.setOnClickListener(new OnExabaseClic(baseId));
            vHolder.tvDel.setOnClickListener(new OnDelbaseClick(baseId,
                    baseName));
            vHolder.tvSort.setOnClickListener(new OnSortbaseClick(baseId));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return convertView;
    }

    static final class ViewHolder {
        private TextView tvBasename, tvBasecode, tvActivaDate, tvPlanArea,
                tvUseState;
        // private LinearLayout item_edit;// 用户编辑项
        private TextView tvExamine, tvDel, tvBoundGrehou, tvSort;
    }

    /**
     * 查看基地
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnExabaseClic
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-7 下午2:28:20
     */
    class OnExabaseClic implements OnClickListener {

        private String baseId;

        public OnExabaseClic(String baseId) {
            // TODO Auto-generated constructor stub
            this.baseId = baseId;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mContext.startActivity(new Intent(mContext, BaseViewActivity.class)
                    .putExtra("baseId", baseId).putExtra("userStatukList", (Serializable) userStatukList));// 编辑基地
        }

    }

    /**
     * 基地删除
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnDelbaseClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-7 上午10:33:47
     */
    class OnDelbaseClick implements OnClickListener {

        private String baseId;
        private String baseName;

        public OnDelbaseClick(String id, String baseName) {
            // TODO Auto-generated constructor stub
            this.baseId = id;
            this.baseName = baseName;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            getCanActivaUser(baseId, baseName);
        }

    }

    /**
     * 基地中大棚排序
     *
     * @author haoruigang
     * @Package com.app.itserv.adapters
     * @project yyshed
     * @ClassName: OnSortbaseClick
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-8 上午10:43:40
     */
    class OnSortbaseClick implements OnClickListener {

        private String baseId;

        public OnSortbaseClick(String id) {
            // TODO Auto-generated constructor stub
            this.baseId = id;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent(mContext, GreenHouseSortActivity.class);
            Bundle budle = new Bundle();
            budle.putString("baseId", baseId);
            // budle.putSerializable("baseObjList", (Serializable) baseObjList);
            intent.putExtras(budle);
            mContext.startActivity(intent);
        }
    }

    /**
     * 删除基地
     *
     * @param baseId
     */
    private void getCanActivaUser(final String baseId, String baseName) {

        dialog = new AlertDialog.Builder(mContext).create();
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        View comfirmDialog = LayoutInflater.from(mContext).inflate(
                R.layout.comfirm_dialog, null);
        dialog.addContentView(comfirmDialog,
                new ViewGroup.LayoutParams((int) (mContext.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(true);
        TextView comfirmContent = (TextView) comfirmDialog
                .findViewById(R.id.tv_comfirmdialog_content);
        quitEnsure = (Button) comfirmDialog.findViewById(R.id.btn_ensurequit);
        quitCancel = (Button) comfirmDialog.findViewById(R.id.btn_cancelquit);
        comfirmContent.setText("你确定要删除该基地(" + baseName + ")吗?");
        // 确认操作
        quitEnsure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                setBaseDelRequest(baseId);
            }
        });
        // 取消操作
        quitCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void setBaseDelRequest(String baseId) {
        String token = PreferencesUtils.getString(mContext, "token");// token
        String currTenantId = PreferencesUtils.getString(mContext,
                "tenantId");// 商户id
        HttpManager.getInstance().basedelete(null, token, currTenantId, baseId, new HttpCallBack<JsonBaseDelObject>((Activity) mContext) {
            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("删除失败");
            }

            @Override
            public void onSuccess(JsonBaseDelObject date) {
                if (null != date) {
                    if (date.isSuccess()) {
                        ToUIEvent toUIEvent = new ToUIEvent(
                                ToUIEvent.UPDATE_BASEMANAGER, null);
                        EventBus.getDefault().post(toUIEvent);
                    } else
                        ToastUtils.makeTextShort(date.getMsg());
                } else {
                    ToastUtils.makeTextShort("删除失败");
                }
            }
        });
    }
}