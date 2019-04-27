package test.cn.sjz.testproject.wuliusystem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.Utils.PreferUtil;
import test.cn.sjz.testproject.base.baseview.BaseActivity;
import test.cn.sjz.testproject.wuliusystem.http.Api;
import test.cn.sjz.testproject.wuliusystem.http.HttpManager;
import test.cn.sjz.testproject.wuliusystem.http.bean.RecordBean;
import test.cn.sjz.testproject.wuliusystem.http.requestbody.GetListBody;

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
                    drawMakers();
                    break;
            }
        }
    };

    List<Marker> markerList = new ArrayList<>();
    //绘制
    private void drawMakers() {

        List<LatLng> latLngs = new ArrayList<>();
        for (int i = 0 ;i<mListData.size();i++){
            LatLng latLng = new LatLng(mListData.get(i).latitude,mListData.get(i).longitude);
            latLngs.add(latLng);
            final Marker marker = aMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(String.valueOf(mListData.get(i).noteTypeId))
                    .snippet(String.valueOf(mListData.get(i).id))
                    .icon(BitmapDescriptorFactory.fromView(getMyBitmap(mListData.get(i).noteTypeId+""))));
            markerList.add(marker);
        }
        Polyline polyline =aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(Color.argb(255, 74, 122, 162)));
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(mListData.get(0).latitude,mListData.get(0).longitude),15));

        // 定义 Marker 点击事件监听
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            // marker 对象被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (markerList.contains(marker)){
                    Intent intent = new Intent(RecordInDayActivity.this,ReCordDetailActivity.class);
                    RecordBean data = new RecordBean();
                    for (int i=0;i<mListData.size();i++){
                        if (mListData.get(i).id == Integer.parseInt(marker.getSnippet()))
                            data = mListData.get(i);
                    }
                    intent.putExtra("data",data);
                    RecordInDayActivity.this.startActivity(intent);
                }
                return true;
            }
        };
        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);

    }


    protected View  getMyBitmap(String pm_val) {
        View view=getLayoutInflater().inflate(R.layout.layout_marker, null);
        TextView tv_val=(TextView) view.findViewById(R.id.marker_tv_val);
        tv_val.setText(pm_val);
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
