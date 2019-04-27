package test.cn.sjz.testproject.testsystem.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseFragment;
import test.cn.sjz.testproject.model.Parcelable.TestData;
import test.cn.sjz.testproject.testsystem.http.Api;
import test.cn.sjz.testproject.testsystem.http.HttpManager;
import test.cn.sjz.testproject.testsystem.http.bean.TypeBean;

/**
 * 巡查
 */

public class PatrolFragment extends BaseFragment{
    private EditText mEtContent,mEtAdvice;
    private Spinner mSpItem;
    private TextView mBtnCommit;
    private List<TypeBean> mListData = new ArrayList<>();
//    private SpinnerAdapter adapter;
    String item,content,advice;
    int typeId = 1;

    HttpManager httpManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_patrol;
    }

    @Override
    protected void initData() {
        httpManager = new HttpManager(getActivity() ,handler);
        httpManager.gettype();
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
//                item = mSpItem
                content = mEtContent.getText().toString();
                advice = mEtAdvice.getText().toString();
                if (item == null || item.equals("")){
                    Toast.makeText(mContext, "请选择巡查项目", Toast.LENGTH_SHORT).show();
                }else if (content == null || content.equals("")){
                    Toast.makeText(mContext, "请填写巡查说明", Toast.LENGTH_SHORT).show();
                }else if (advice == null || advice.equals("")){
                    Toast.makeText(mContext, "请提一些建议", Toast.LENGTH_SHORT).show();
                }else {
                    //发送提交请求

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

                    break;
                case Api.ADD_NOTE_F:
                    break;

            }
        }
    };
}
