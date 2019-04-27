package test.cn.sjz.testproject.wuliusystem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.wuliusystem.http.Api;
import test.cn.sjz.testproject.wuliusystem.http.HttpManager;
import test.cn.sjz.testproject.wuliusystem.http.bean.RecordBean;

public class AddTrackActivity extends BaseActivity {
    private TextView mTvBack,mTvStatus,mBtnCommit;
    private RecyclerView recyclerView;

    private HttpManager httpManager;
    private List<RecordBean> mListData = new ArrayList<>();

    String code;
    @Override
    public int getLayoutID() {
        return R.layout.activity_add_track;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        mTvBack = (TextView)findViewById(R.id.tv_back);
    }

    @Override
    public void initDate() {

        Intent intent = getIntent();
        code = intent.getStringExtra("code");
        httpManager = new HttpManager(this,handler);

        if (code != null && code.equals(""))
        {
            httpManager.gettrack(code);
        }
        else Toast.makeText(this, "code为空，请重新操作", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void iniListener() {
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Api.FAILED:
                case Api.GET_INFO_F:
                case Api.ADD_TRACK_F:
                    String failed=(String )msg.obj;
                    Toast.makeText(AddTrackActivity.this,failed , Toast.LENGTH_SHORT).show();
                    break;
                case Api.GET_INFO_S:


                    break;
                case Api.ADD_TRACK_S:
                    Toast.makeText(AddTrackActivity.this,"成功" , Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
