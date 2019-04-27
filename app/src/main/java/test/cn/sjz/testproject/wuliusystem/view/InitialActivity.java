package test.cn.sjz.testproject.wuliusystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseActivity;

/**
 * 启动页
 */

public class InitialActivity extends BaseActivity {
    private TextView mTvCountDown;
    private LinearLayout mLlIcon;
    private int countdown = 3;
    private Animation anim;
    @Override
    public int getLayoutID() {
        return R.layout.activity_initial;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        anim = AnimationUtils.loadAnimation(this,R.anim.scale_alpha_anim);
        mLlIcon = (LinearLayout) findViewById(R.id.ll_icon);
        mLlIcon.startAnimation(anim);
        mTvCountDown = (TextView)findViewById(R.id.tv_count_down);
        mTvCountDown.post(setTime);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void iniListener() {
        mTvCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvCountDown.removeCallbacks(setTime);
                Intent intent = new Intent(InitialActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private Runnable setTime = new Runnable() { //验证码倒计时
        public void run() {
            if (countdown >= 0){
                mTvCountDown.setText(String.valueOf(countdown)+"跳过");
                countdown -= 1;
                mTvCountDown.postDelayed(this,1000);
            }else {
                Intent intent = new Intent(InitialActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }

        }
    };

}
