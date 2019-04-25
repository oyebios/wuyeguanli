package test.cn.sjz.testproject.testsystem;

import android.support.v7.widget.RecyclerView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseFragment;

/**
 * Created by lwd on 2019/4/25.
 */

public class RecordFragment extends BaseFragment implements PullToRefreshRecyclerView.OnRefreshListener2{
    PullToRefreshRecyclerView pullView;
    RecyclerView mRcvRecord;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        pullView = (PullToRefreshRecyclerView)mRootView.findViewById(R.id.rcv_record);
        pullView.setMode(PullToRefreshBase.Mode.BOTH);
        pullView.setOnRefreshListener(this);
        mRcvRecord = pullView.getRefreshableView();

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
