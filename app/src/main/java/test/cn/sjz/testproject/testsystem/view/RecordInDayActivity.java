package test.cn.sjz.testproject.testsystem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.Utils.PreferUtil;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.testsystem.http.Api;
import test.cn.sjz.testproject.testsystem.http.HttpManager;
import test.cn.sjz.testproject.testsystem.http.bean.RecordBean;
import test.cn.sjz.testproject.testsystem.http.requestbody.GetListBody;

/**
 * Created by lwd on 2019/4/27.
 * Explain
 */

public class RecordInDayActivity extends BaseActivity {
    private TextView mTvBack,mTvTitle;
    private MapView mapView;
    private AMap aMap;
    private HttpManager httpManager;
    private List<RecordBean> mListData = new ArrayList<>();
    @Override
    public int getLayoutID() {
        return R.layout.activity_record_in_day;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.mv_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);
        mTvBack = (TextView)findViewById(R.id.tv_back);
        mTvTitle = (TextView)findViewById(R.id.tv_date);
    }

    @Override
    public void initDate() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        Intent intent = getIntent();
        String time = intent.getStringExtra("time");
        mTvTitle.setText(time);
        httpManager = new HttpManager(this,handler);

        int uid = PreferUtil.getInstance().getInt("uid",-1);
        if (uid != -1)
        {
            GetListBody getListBody = new GetListBody(time,uid);
            httpManager.getlist(getListBody);
        }
        else Toast.makeText(this, "uid错误，请重新登录", Toast.LENGTH_SHORT).show();

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
                case Api.GET_LIST_F:
                    String failed=(String )msg.obj;
                    Toast.makeText(RecordInDayActivity.this,failed , Toast.LENGTH_SHORT).show();
                    break;
                case Api.GET_LIST_S:
                    mListData.addAll((List<RecordBean>)msg.obj);
                    break;
            }
        }
    };
}
