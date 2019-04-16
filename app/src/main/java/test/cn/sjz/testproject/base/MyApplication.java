package test.cn.sjz.testproject.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import static test.cn.sjz.testproject.base.GlobalConfig.ISDEBUG;

/**
 * Created by lwd on 2019/4/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (ISDEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
