package test.cn.sjz.testproject.testsystem.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseFragment;

/**
 * Created by lwd on 2019/4/25.
 */

public class MeFragment extends BaseFragment {
//    private MapView mv_map;
//    private AMap aMap;

    private TextView mTvUser,mTvJoinTime,mTvCountToday,mTvCountTotal,mBtnLoginOut;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
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

    }
}
