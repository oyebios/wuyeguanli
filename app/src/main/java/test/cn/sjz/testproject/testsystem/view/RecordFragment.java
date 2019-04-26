package test.cn.sjz.testproject.testsystem.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseFragment;

/**
 * Created by lwd on 2019/4/25.
 */

public class RecordFragment extends BaseFragment implements PullToRefreshRecyclerView.OnRefreshListener2{
    private PullToRefreshRecyclerView pullView;
    private RecyclerView mRcvRecord;
    private RecyclerView.Adapter mAdapter;

    public static final int QUERY_TYPE_REFRESH = 0;
    public static final int QUERY_TYPE_LOAD_MORE = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        pullView = (PullToRefreshRecyclerView)mRootView.findViewById(R.id.rcv_record);
        pullView.setMode(PullToRefreshBase.Mode.BOTH);
        pullView.setOnRefreshListener(this);
        mRcvRecord = pullView.getRefreshableView();

    }

    @Override
    protected void initListener() {

    }
    private int startPos = 0;
    private int count = 10;
    private void query_record(int type){
        if (type == QUERY_TYPE_REFRESH){
            startPos = 0;
        }
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        query_record(QUERY_TYPE_REFRESH);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        query_record(QUERY_TYPE_LOAD_MORE);
    }
}
