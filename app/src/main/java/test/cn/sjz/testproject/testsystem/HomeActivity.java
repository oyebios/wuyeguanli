package test.cn.sjz.testproject.testsystem;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


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


//    private HomeFragment homeFragment;

    private long exitTime;

    @Override
    public int getLayoutID() {
        return R.layout.activity_home_page;
    }

    @Override
    public void initView() {

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
//    public void select(int id) {
//
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//        hideFragment(transaction);
//
//        // 点击高亮
//        switch (id) {
//            case 0:
//                if (homeFragment == null) {
//                    homeFragment = new HomeFragment();
//                    transaction.add(R.id.id_content, homeFragment);
//                } else {
//                    transaction.show(homeFragment);
//                    //homeFragment.fetchData();
//                }
//                iv_home.setImageResource(R.drawable.home_press);
//                tv_home.setTextColor(getResources().getColor(R.color.main_color_green));
//
//                break;
//            case 1:
//                if (exchangeFragment == null) {
//                    exchangeFragment = new ExchangeFragment();
//                    transaction.add(R.id.id_content, exchangeFragment);
//                } else {
//                    transaction.show(exchangeFragment);
//                }
//                iv_exchange.setImageResource(R.drawable.exchange_press);
//                tv_exchange.setTextColor(getResources().getColor(R.color.main_color_green));
//                break;
//            case 2:
//                if (meFragment == null) {
//                    meFragment = new MeFragment();
//                    transaction.add(R.id.id_content, meFragment);
//
//                } else {
//                    transaction.show(meFragment);
//                 //   meFragment.fetchData();
//                }
//                iv_me.setImageResource(R.drawable.me_press);
//                tv_me.setTextColor(getResources().getColor(R.color.main_color_green));
//                break;
//            default:
//                break;
//        }
//
//        transaction.commit();
//    }
//
//    private void hideFragment(FragmentTransaction transaction) {
//        if (homeFragment != null) {
//            transaction.hide(homeFragment);
//        }
//        if (exchangeFragment != null) {
//            transaction.hide(exchangeFragment);
//        }
//        if (meFragment != null) {
//            transaction.hide(meFragment);
//
//        }
//    }

    public void resetTab() {
        mFtvPage1.setTextColor(getResources().getColor(R.color.btn_pressed));
        mFtvPage2.setTextColor(getResources().getColor(R.color.btn_pressed));
        mFtvPage3.setTextColor(getResources().getColor(R.color.btn_pressed));
        mTvPage1.setTextColor(getResources().getColor(R.color.btn_pressed));
        mTvPage2.setTextColor(getResources().getColor(R.color.btn_pressed));
        mTvPage3.setTextColor(getResources().getColor(R.color.btn_pressed));

    }

    @Override
    public void onClick(View v) {
        resetTab();
        switch (v.getId()) {
            case R.id.ll_page_1:
//                select(0);
                break;
            case R.id.ll_page_2:
//                select(1);
                break;
            case R.id.ll_page_3:
//                select(2);
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
        System.exit(0);
    }

}
