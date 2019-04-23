package test.cn.sjz.testproject.Music;

import com.alibaba.android.arouter.facade.annotation.Route;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.ARouterPath;
import test.cn.sjz.testproject.base.baseview.BaseActivity;

/**
 * Created by lwd on 2019/4/23.
 */
@Route(path = ARouterPath.ACTIVITY_PIANO)
public class ActivityPiano extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.avtivity_piano;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {

    }

    @Override
    public void iniListener() {

    }
}
