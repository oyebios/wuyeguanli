package test.cn.sjz.testproject.testsystem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.testsystem.http.Api;
import test.cn.sjz.testproject.testsystem.http.HttpManager;
import test.cn.sjz.testproject.testsystem.http.bean.RecordBean;
import test.cn.sjz.testproject.testsystem.http.bean.TypeBean;


public class ReCordDetailActivity extends BaseActivity {
    private TextView mTvItem,mTvContent,mTvAdvice,mTvStatus;
    private TextView mTvBack;
    private List<TypeBean> mListData = new ArrayList<>();
    int id = -1;
    HttpManager httpManager;
    @Override
    public int getLayoutID() {
        return R.layout.activity_patrol_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTvItem = (TextView)findViewById(R.id.tv_item);
        mTvContent = (TextView)findViewById(R.id.tv_content);
        mTvAdvice= (TextView)findViewById(R.id.tv_advice);
        mTvBack = (TextView)findViewById(R.id.tv_back);
        mTvStatus = (TextView)findViewById(R.id.tv_status);
    }

    @Override
    public void initDate() {
        Intent intent = getIntent();
        RecordBean data = (RecordBean)intent.getSerializableExtra("data");
        if (data!=null){
            id = data.noteTypeId;
            mTvContent.setText(data.content);
            mTvAdvice.setText(data.treatment);
            mTvStatus.setText(data.reviewContent);
        }
        httpManager = new HttpManager(this ,handler);
        httpManager.gettype();


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
                case Api.GET_TYPE_F:
                    String failed=(String )msg.obj;
                    Toast.makeText(ReCordDetailActivity.this,failed , Toast.LENGTH_SHORT).show();
                    break;
                case Api.GET_TYPE_S:
                    mListData.addAll((List<TypeBean>)msg.obj);
                    for (int i = 0;i <mListData.size();i++){
                       if (mListData.get(i).getId() == id ){
                           mTvItem.setText(mListData.get(i).getTypeName());
                       }
                    }
                    break;
            }
        }
    };
}
