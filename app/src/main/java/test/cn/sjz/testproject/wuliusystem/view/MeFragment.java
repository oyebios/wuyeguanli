package test.cn.sjz.testproject.wuliusystem.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.Utils.PreferUtil;
import test.cn.sjz.testproject.base.baseview.BaseFragment;
import test.cn.sjz.testproject.wuliusystem.http.Api;
import test.cn.sjz.testproject.wuliusystem.http.HttpManager;
import test.cn.sjz.testproject.wuliusystem.http.bean.RecordListBean;

/**
 * 我的
 */

public class MeFragment extends BaseFragment {
    private TextView mTvUser,mTvJoinTime,mTvCountToday,mTvCountTotal,mBtnLoginOut;
    private HttpManager httpManager;
    private List<RecordListBean> mListData = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        httpManager = new HttpManager(getActivity(),handler);
        int uid = PreferUtil.getInstance().getInt("uid",-1);
        if (uid != -1) httpManager.getcount(uid);
        else Toast.makeText(mContext, "uid错误，请重新登录", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        mTvUser = (TextView)v.findViewById(R.id.tv_user);
        mTvJoinTime = (TextView)v.findViewById(R.id.tv_join_time);
        mTvCountToday = (TextView)v.findViewById(R.id.tv_count_today);
        mTvCountTotal = (TextView)v.findViewById(R.id.tv_count_total);
        mBtnLoginOut = (TextView)v.findViewById(R.id.btn_login_out);
    }

    @Override
    protected void initListener() {
        mBtnLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    //提示框
    private void showDialog() {
        // 创建构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        // 设置参数
        builder.setTitle("提示")
                .setMessage("退出登录将清空本地数据，确认退出？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {// 积极
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loginout();
                    }})
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                     }});
        builder.create().show();
    }

    //退出登录
    private void loginout() {
        PreferUtil.getInstance().putString("userName","");
        PreferUtil.getInstance().putString("passWord","");
        PreferUtil.getInstance().putLong("createtime",0);
        PreferUtil.getInstance().putInt("uid",-1);
        Intent intent = new Intent(mContext,LoginActivity.class );
        startActivity(intent);
        getActivity().finish();
    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Api.FAILED:
                case Api.GET_COUNT_F:
                    String failed=(String )msg.obj;
                    Toast.makeText(mContext,failed , Toast.LENGTH_SHORT).show();
                    break;
                case Api.GET_COUNT_S:
                    mListData.clear();
                    mListData.addAll((List<RecordListBean>)msg.obj);
                    int i =0;
                    Date date = new Date(System.currentTimeMillis());
                    String timenow  = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    int countToday = 0;
                    for (int j =0 ;j<mListData.size();j++){
                        i+= mListData.get(j).getNum();
                        if (timenow.equals(mListData.get(j).getTime()))
                            countToday = mListData.get(j).getNum();
                    }
                    long time = PreferUtil.getInstance().getLong("createtime",0);
                    String timestr = "";
                    if (time != 0){
                        timestr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(time);
                    }
                    mTvJoinTime.setText(timestr);
                    mTvCountToday.setText(String.valueOf(countToday));
                    mTvCountTotal.setText(String.valueOf(i));
                    break;
            }
        }
    };
}
