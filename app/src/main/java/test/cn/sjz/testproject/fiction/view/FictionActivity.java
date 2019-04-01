package test.cn.sjz.testproject.fiction.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import test.cn.sjz.testproject.fiction.contract.FictionContract;
import test.cn.sjz.testproject.fiction.presenter.FictionIPresenter;

/**
 * Created by lwd on 2019/4/1.
 *
 */

public class FictionActivity extends AppCompatActivity implements FictionContract.FictionIView{
    private FictionContract.IPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FictionIPresenter(this);
        presenter.getIndex();
    }

    @Override
    public void showIndex(String s) {

    }

    @Override
    public void setPresenter(FictionContract.IPresenter iPresenter) {
        this.presenter = iPresenter;
    }
}
