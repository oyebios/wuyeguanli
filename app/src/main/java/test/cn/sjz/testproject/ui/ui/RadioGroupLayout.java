package test.cn.sjz.testproject.ui.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.cn.sjz.testproject.R;


/**
 * Created by lwd on 2019/3/27.
 */

public class RadioGroupLayout extends LinearLayout{
    private Context mContext;
    private LinearLayout mRootLayout;
    private List<Object> mListData = new ArrayList<>();
    private LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f);
    private LayoutParams params_tv = new LayoutParams(100, ViewGroup.LayoutParams.WRAP_CONTENT);
    private List<TextView> mListTv = new ArrayList<>();
    private  int line_num = 4;
    private int styleId = -1;
    private int selectIndex = -1;
    private onSelectListener listener;

    public RadioGroupLayout(Context context ) {
        super(context);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_radio_group,this);
        mRootLayout = (LinearLayout)findViewById(R.id.ll_root);
//        params.gravity = Gravity.CENTER;

    }

    public RadioGroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
      LayoutInflater.from(context).inflate(R.layout.layout_radio_group,this);
        mRootLayout = (LinearLayout)findViewById(R.id.ll_root);
        mRootLayout.setGravity(Gravity.CENTER_VERTICAL);
//        params.gravity = Gravity.CENTER;
    }

    public RadioGroupLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setTvStyle(int styleId , @Nullable LayoutParams  params_tv){
        this.styleId = styleId;
        if (params_tv!= null)
            this.params_tv = params_tv;
    }
    public void setData( List listData){
        //清空数据
        mListData.clear();
        mListTv.clear();
        mRootLayout.removeAllViews();
        mListData .addAll(listData);

        for (Object text :mListData){
            TextView tv = new TextView(mContext);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.zero_capability_shape_normal);
            tv.setTextColor(ContextCompat.getColor(mContext, R.color.text_deliver_name));
            if (styleId != -1)
                tv.setTextAppearance(mContext,styleId);
            tv.setText(String .valueOf(text));
            mListTv.add(tv);
        }
    }
    public void initView() {
        int lines = mListData.size() / line_num ;
        int remainder = mListData.size() % line_num ;
        for (int i = 0;i < lines;i++){
            LinearLayout layout_line = new LinearLayout(mContext);
            mRootLayout.addView(layout_line,params);
            for (int j = 0;j < line_num ; j++){
                LinearLayout rect = new LinearLayout(mContext);
                layout_line.addView(rect,params);
                rect.setGravity(Gravity.LEFT);
                rect.addView(mListTv.get(i*line_num + j),params_tv);
            }

        }
        LinearLayout layout_line = new LinearLayout(mContext);
        for (int k =0 ,j= 0;j <line_num ;j++,k++){
            LinearLayout rect = new LinearLayout(mContext);
            layout_line.addView(rect,params);
            rect.setGravity(Gravity.LEFT);
            if (k < remainder)
            rect.addView(mListTv.get(lines*line_num + k),params_tv);
        }
        mRootLayout.addView(layout_line);

        for(int i = 0;i< mListTv.size();i++){
            final int finalI = i;
            mListTv.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI == selectIndex){
                        mListTv.get(selectIndex).setBackgroundResource(R.drawable.zero_capability_shape_normal);
                        mListTv.get(selectIndex).setTextColor(ContextCompat.getColor(mContext, R.color.text_deliver_name));
                        selectIndex = -1;
                    }else {
                        mListTv.get(finalI).setBackgroundResource(R.drawable.zero_capability_shape_selected);
                        mListTv.get(finalI).setTextColor(ContextCompat.getColor(mContext, R.color.white));
                        if (selectIndex != -1){
                            mListTv.get(selectIndex).setBackgroundResource(R.drawable.zero_capability_shape_normal);
                            mListTv.get(selectIndex).setTextColor(ContextCompat.getColor(mContext, R.color.text_deliver_name));
                        }
                        selectIndex = finalI;
                    }
                    listener.onSelect(selectIndex);
                }
            });
        }
    }
    public void setOnSelectListener(onSelectListener listener){
        this.listener = listener;
    }
    public interface onSelectListener{
        void onSelect(int selectIndex);
    }

}
