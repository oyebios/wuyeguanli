package test.cn.sjz.testproject.testsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import test.cn.sjz.testproject.R;
import test.cn.sjz.testproject.base.baseview.BaseFragment;

/**
 * Created by lwd on 2019/4/24.
 */

public class PatrolFragment extends BaseFragment{
    private EditText mEtContent,mEtAdvice;
    private Spinner mSpItem;
    private Button mBtnCommit;

    String item,content,advice;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_patrol;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        mEtContent = (EditText)mRootView.findViewById(R.id.ev_content);
        mEtAdvice = (EditText)mRootView.findViewById(R.id.ev_advice);
        mSpItem = (Spinner)mRootView.findViewById(R.id.sp_item);
        mBtnCommit = (Button)mRootView.findViewById(R.id.btn_commit);
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
    }
}
