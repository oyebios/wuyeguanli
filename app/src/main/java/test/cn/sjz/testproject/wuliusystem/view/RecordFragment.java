package test.cn.sjz.testproject.wuliusystem.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.Utils.PreferUtil;
import test.cn.sjz.testproject.base.baseview.BaseFragment;
import test.cn.sjz.testproject.wuliusystem.RecordAdapter;
import test.cn.sjz.testproject.wuliusystem.http.Api;
import test.cn.sjz.testproject.wuliusystem.http.HttpManager;
import test.cn.sjz.testproject.wuliusystem.http.bean.RecordListBean;

/**
 * 记录
 */

public class RecordFragment extends BaseFragment implements PullToRefreshRecyclerView.OnRefreshListener2{
    private PullToRefreshRecyclerView pullView;
    private RecyclerView mRcvRecord;
    private RecyclerView.Adapter mAdapter;
    private List<RecordListBean> mListData = new ArrayList<>();

    HttpManager httpManager ;

    public static final int QUERY_TYPE_REFRESH = 0;
    public static final int QUERY_TYPE_LOAD_MORE = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initData() {
        httpManager = new HttpManager(getActivity(),handler);
        query_record(QUERY_TYPE_REFRESH);
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        pullView = (PullToRefreshRecyclerView)mRootView.findViewById(R.id.rcv_record);
        pullView.setMode(PullToRefreshBase.Mode.BOTH);
        pullView.setOnRefreshListener(this);
        mRcvRecord = pullView.getRefreshableView();
        mAdapter = new RecordAdapter(mContext,mListData);
        mRcvRecord.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRcvRecord.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void initListener() {

    }
    private int startPos = 0;
    private int count = 10;
    private void query_record(int type){
//        if (type == QUERY_TYPE_REFRESH){
//            startPos = 0;
//        }

        int uid = PreferUtil.getInstance().getInt("uid",-1);
        if (uid != -1) httpManager.getcount(uid);
        else Toast.makeText(mContext, "uid错误，请重新登录", Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Api.FAILED:
                case Api.GET_COUNT_F:
                    pullView.onRefreshComplete();
                    String failed=(String )msg.obj;
                    Toast.makeText(mContext,failed , Toast.LENGTH_SHORT).show();
                    break;
                case Api.GET_COUNT_S:
                    pullView.onRefreshComplete();
                    mListData.clear();
                    mListData.addAll((List<RecordListBean>)msg.obj);
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        query_record(QUERY_TYPE_REFRESH);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        query_record(QUERY_TYPE_LOAD_MORE);
    }
}
