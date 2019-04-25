package test.cn.sjz.testproject.testsystem;

import android.content.Intent;
import android.service.quicksettings.Tile;
import android.widget.TextView;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseActivity;

/**
 * Created by lwd on 2019/4/25.
 */

public class InitialActivity extends BaseActivity {
    private TextView mTvCountDown;
    private int countdown = 3;
    @Override
    public int getLayoutID() {
        return R.layout.activity_initial;
    }

    @Override
    public void initView() {
        mTvCountDown = (TextView)findViewById(R.id.tv_count_down);
        mTvCountDown.post(setTime);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void iniListener() {

    }

    private Runnable setTime = new Runnable() { //验证码倒计时
        public void run() {
            if (countdown >= 0){
                mTvCountDown.setText(String.valueOf(countdown)+"跳过");
                countdown -= 1;
                mTvCountDown.postDelayed(this,1000);
            }else {
                Intent intent = new Intent(InitialActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }

        }
    };

}
