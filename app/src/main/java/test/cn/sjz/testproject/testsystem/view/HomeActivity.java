package test.cn.sjz.testproject.testsystem.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.customview.FontTextView;

public class HomeActivity extends BaseActivity implements View.OnClickListener {



    //tab底部栏的控件
    private LinearLayout mLlPageOne;
    private LinearLayout mLlPageTwo;
    private LinearLayout mLlPageThree;

    private FontTextView mFtvPage1;
    private FontTextView mFtvPage2;
    private FontTextView mFtvPage3;

    private TextView mTvPage1;
    private TextView mTvPage2;
    private TextView mTvPage3;


    private PatrolFragment patrolFragment;
    private RecordFragment recordFragment;
    private MeFragment meFragment;

    private long exitTime;

    @Override
    public int getLayoutID() {
        return R.layout.activity_home_page;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        //初始化view   tab
        mLlPageOne = (LinearLayout)findViewById(R.id.ll_page_1);
        mLlPageTwo = (LinearLayout)findViewById(R.id.ll_page_2);
        mLlPageThree = (LinearLayout)findViewById(R.id.ll_page_3);

        mFtvPage1 = (FontTextView)findViewById(R.id.ftv_page_1);
        mFtvPage2 = (FontTextView)findViewById(R.id.ftv_page_2);
        mFtvPage3 = (FontTextView)findViewById(R.id.ftv_page_3);

        mTvPage1 = (TextView)findViewById(R.id.tv_page_1);
        mTvPage2 = (TextView)findViewById(R.id.tv_page_2);
        mTvPage3 = (TextView)findViewById(R.id.tv_page_3);



    }

    @Override
    public void initDate() {
        select(0);
    }

    @Override
    public void iniListener() {
        mLlPageOne.setOnClickListener(this);
        mLlPageTwo.setOnClickListener(this);
        mLlPageThree.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


//
    public void select(int id) {
        resetTab();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);

        // 点击高亮
        switch (id) {
            case 0:
                if (patrolFragment == null) {
                    patrolFragment = new PatrolFragment();
                    transaction.add(R.id.rl_content, patrolFragment);
                } else {
                    transaction.show(patrolFragment);
                }
                mFtvPage1.setTextColor(getResources().getColor(R.color.light_text_color));
                mTvPage1.setTextColor(getResources().getColor(R.color.light_text_color));

                break;
            case 1:
                if (recordFragment == null) {
                    recordFragment = new RecordFragment();
                    transaction.add(R.id.rl_content, recordFragment);
                } else {
                    transaction.show(recordFragment);
                }
                mFtvPage2.setTextColor(getResources().getColor(R.color.light_text_color));
                mTvPage2.setTextColor(getResources().getColor(R.color.light_text_color));
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.rl_content, meFragment);

                } else {
                    transaction.show(meFragment);
                }
                mFtvPage3.setTextColor(getResources().getColor(R.color.light_text_color));
                mTvPage3.setTextColor(getResources().getColor(R.color.light_text_color));
                break;
            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (patrolFragment != null) {
            transaction.hide(patrolFragment);
        }
        if (recordFragment != null) {
            transaction.hide(recordFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);

        }
    }

    public void resetTab() {
        mFtvPage1.setTextColor(getResources().getColor(R.color.light_gray));
        mFtvPage2.setTextColor(getResources().getColor(R.color.light_gray));
        mFtvPage3.setTextColor(getResources().getColor(R.color.light_gray));
        mTvPage1.setTextColor(getResources().getColor(R.color.light_gray));
        mTvPage2.setTextColor(getResources().getColor(R.color.light_gray));
        mTvPage3.setTextColor(getResources().getColor(R.color.light_gray));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_page_1:
                select(0);
                break;
            case R.id.ll_page_2:
                select(1);
                break;
            case R.id.ll_page_3:
                select(2);
                break;
        }
    }
    /** 监听手机按键 */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if ((keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else if ((System.currentTimeMillis() - exitTime) > 0) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        }
    }
    /**
     *
     * @param permissions
     * @since 2.5.0
     *
     */
    private void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class,
                            int.class});

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23){
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer)checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean)shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少必要权限。\\n请点击\"设置\"-\"权限\"-打开所需权限。");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };
    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;



}
