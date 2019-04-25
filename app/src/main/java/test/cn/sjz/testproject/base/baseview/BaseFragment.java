package test.cn.sjz.testproject.base.baseview;

import android.content.Context;
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
    protected Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(),container,false);
        initView(mRootView,savedInstanceState);
        initData();
        initListener();
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected  abstract int getLayoutId();
    protected abstract void initView(View v, Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initListener();


}
