package test.cn.sjz.testproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import test.cn.sjz.testproject.Utils.FileChooseUtil;
import test.cn.sjz.testproject.base.baseview.BaseFragment;
import test.cn.sjz.testproject.model.Parcelable.TestData;

import static android.app.Activity.RESULT_OK;
import static test.cn.sjz.testproject.base.GlobalConfig.REQUEST_CHOOSEFILE;

/**
 * Created by lwd on 2019/4/18.
 */

public class FragmentMain extends BaseFragment{
    Button mbtnChoose;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mbtnChoose = (Button)mRootView.findViewById(R.id.btn_choose_file);
    }
    @Override
    protected void initListener() {
        mbtnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");//选择图片
                //intent.setType(“audio/*”); //选择音频
                //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                //intent.setType(“video/*;image/*”);//同时选择视频和图片
//                intent.setType("*/*");//无类型限制
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, REQUEST_CHOOSEFILE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch(requestCode){
                case REQUEST_CHOOSEFILE:
                    Uri uri=data.getData();
                    String chooseFilePath= FileChooseUtil.getInstance(mContext).getChooseFileResultPath(uri);
                    Log.d("testfile choose","选择文件返回："+chooseFilePath);
                    break;
            }
        }
    }
}
