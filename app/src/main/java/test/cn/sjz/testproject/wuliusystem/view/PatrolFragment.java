package test.cn.sjz.testproject.wuliusystem.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.Utils.PreferUtil;
import test.cn.sjz.testproject.base.baseview.BaseFragment;
import test.cn.sjz.testproject.wuliusystem.http.Api;
import test.cn.sjz.testproject.wuliusystem.http.HttpManager;
import test.cn.sjz.testproject.wuliusystem.http.bean.TypeBean;
import test.cn.sjz.testproject.wuliusystem.http.requestbody.AddNoteBody;

import static test.cn.sjz.testproject.base.MyApplication.appContext;

/**
 * 巡查
 */

public class PatrolFragment extends BaseFragment{
    private EditText mEtContent,mEtAdvice;
    private Spinner mSpItem;
    private TextView mBtnCommit;
    private List<TypeBean> mListData = new ArrayList<>();
    String item,content,advice;
    int typeId = -1;

    HttpManager httpManager;

    AMapLocation location ;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_patrol;
    }

    @Override
    protected void initData() {
        httpManager = new HttpManager(getActivity() ,handler);
        httpManager.gettype();

        //初始化定位
        mLocationClient = new AMapLocationClient(appContext);
        //声明AMapLocationClientOption对象
         AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
         mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
         mLocationOption.setOnceLocationLatest(true);

        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                location = aMapLocation;
                Log.d("testLocation",String.valueOf(aMapLocation.getLatitude())+"   ,"+String.valueOf(aMapLocation.getLatitude()));
            }
        });

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        mEtContent = (EditText)mRootView.findViewById(R.id.ev_content);
        mEtAdvice = (EditText)mRootView.findViewById(R.id.ev_advice);
        mSpItem = (Spinner)mRootView.findViewById(R.id.sp_item);
        mBtnCommit = (TextView)mRootView.findViewById(R.id.btn_commit);
    }

    @Override
    protected void initListener() {
        mBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = mEtContent.getText().toString();
                advice = mEtAdvice.getText().toString();
                if (typeId == -1 ){
                    Toast.makeText(mContext, "请选择巡查项目", Toast.LENGTH_SHORT).show();
                }else if (content == null || content.equals("")){
                    Toast.makeText(mContext, "请填写巡查说明", Toast.LENGTH_SHORT).show();
                }else if (advice == null || advice.equals("")){
                    Toast.makeText(mContext, "请提一些建议", Toast.LENGTH_SHORT).show();
                }else {
                    //发送提交请求
                    getLocation();
                }
            }
        });

        mSpItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeId = mListData.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                case Api.ADD_NOTE_F:
                    String failed=(String )msg.obj;
                    Toast.makeText(mContext,failed , Toast.LENGTH_SHORT).show();
                    break;
                case Api.GET_TYPE_S:
                    mListData.addAll((List<TypeBean>)msg.obj);
                    List<String> strList = new ArrayList<>();
                    for (int i = 0;i <mListData.size();i++){
                        strList.add(mListData.get(i).getTypeName());
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,strList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpItem.setAdapter(arrayAdapter);
                    break;

                case Api.ADD_NOTE_S:
                    Toast.makeText(mContext, "提交成功", Toast.LENGTH_SHORT).show();
                    mEtAdvice.setText("");
                    mEtContent.setText("");
                    break;

            }
        }
    };

    public void getLocation() {
        if (location != null){
            AddNoteBody addNoteBody = new AddNoteBody();
            addNoteBody.setContent(content);
            addNoteBody.setLatitude(location.getLatitude());
            addNoteBody.setLongitude(location.getLongitude());
            addNoteBody.setTreatment(advice);
            addNoteBody.setNoteTypeId(typeId);
            String name = PreferUtil.getInstance().getString("userName","");
            int  uid = PreferUtil.getInstance().getInt("uid",-1);
            if (name.equals("")||uid == -1){
                Toast.makeText(mContext, "身份失效请重新登录", Toast.LENGTH_SHORT).show();
            }else {
                addNoteBody.setName(name);
                addNoteBody.setUid(uid);
            }
            httpManager.addnote(addNoteBody);
        }else {
            mLocationClient.startLocation();
            Toast.makeText(mContext, "定位失败请重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLocationClient.stopLocation();
        mLocationClient.onDestroy();
    }
}
