package test.cn.sjz.testproject.base.baseview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

/**
 * Created by lwd on 2019/4/22.
 */

public abstract class BaseFragment extends Fragment {
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(),container,false);
        initView();
        initData();
        return mRootView;
    }
    protected  abstract int getLayoutId();
    protected abstract void initData();
    protected abstract void initView();


}
