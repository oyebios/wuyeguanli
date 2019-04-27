package test.cn.sjz.testproject.fiction.view;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.ARouterPath;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.fiction.contract.FictionContract;
import test.cn.sjz.testproject.fiction.presenter.FictionIPresenter;

/**
 * Created by lwd on 2019/4/1.
 *
 */
@Route(path = ARouterPath.ACTIVITY_FICTION)
public class FictionActivity extends BaseActivity implements FictionContract.FictionIView{
    private FictionContract.IPresenter presenter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_fiction;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initDate() {
        presenter = new FictionIPresenter(this);
        presenter.getIndex();
    }

    @Override
    public void iniListener() {

    }

    @Override
    public void showIndex(String s) {

    }

    @Override
    public void setPresenter(FictionContract.IPresenter iPresenter) {
        this.presenter = iPresenter;
    }
}
