package test.cn.sjz.testproject.base.baseview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lwd on 2019/4/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initView(savedInstanceState);
        initDate();
        iniListener();
    }
    public abstract int getLayoutID();
    public abstract void initView(Bundle savedInstanceState);
    public abstract void initDate();
    public abstract void iniListener();
}
