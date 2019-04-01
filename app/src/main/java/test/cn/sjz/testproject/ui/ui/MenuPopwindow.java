package test.cn.sjz.testproject.ui.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lwd on 2019/3/28.
 */

public  class MenuPopwindow {
    private Context mContext;

    private PopupWindow mPopwindow;

    private View mContentView;

    private ViewGroup.LayoutParams params;
    public MenuPopwindow(Context context,View contentView){
        this.mContext = context;
        mContentView = contentView;
        //初始化  默认宽高自适应
        mPopwindow = new PopupWindow( contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    //设置布局参数
    public MenuPopwindow(ViewGroup.LayoutParams params){
        this.params = params;
        mPopwindow.setWidth(params.width);
        mPopwindow.setHeight(params.height);
    }

    //设置背景   设置背景后 contentview外会包裹一层PopupViewContainer，重写onkey以及ontouch，不设置则无法响应点击外部的事件
    public MenuPopwindow setBackground(Drawable drawable){
        mPopwindow.setBackgroundDrawable(drawable);
        return this;
    }
    //设置弹出时是否获取焦点
    public MenuPopwindow setFocusable(boolean var){
        mPopwindow.setFocusable(var);
        return this;
    }

    //设置是否接收点击外部的事件
    public MenuPopwindow setOutsideTouchable(boolean var){
        mPopwindow.setOutsideTouchable(var);
        return this;
    }

    //设置点击外部事件listener
    public MenuPopwindow setOutsideTouchListener(View.OnTouchListener i){
        mPopwindow.setTouchInterceptor(i);
        return this;
    }

    //设置animation
    public MenuPopwindow setAnimation(int style){
        mPopwindow.setAnimationStyle(style);
        return this;
    }
    //设置布局中textview的内容
    public MenuPopwindow setTvText(int id ,String text){
        TextView tv = (TextView)mContentView.findViewById(id);
        tv.setText(text);
        return this;
    }


    public MenuPopwindow setClickListener(int id ,final boolean isClickdismiss, final OnClickCallBack onClickCallBack){
        View view= mContentView.findViewById(id);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCallBack.OnClick();
                if (isClickdismiss)mPopwindow.dismiss();
            }
        });

        return this;
    }

    //在父view中按照相对位置显示
    public void showAtLocation(View v,int gravity ,int x,int y){
        mPopwindow.showAtLocation(v,gravity,x,y);
    }

    //显示
    public void showAsDropdown(View view ){
        mPopwindow.showAsDropDown(view );
    }
    public void showAsDropdown(View view,int x ,int y){
        mPopwindow.showAsDropDown(view,x,y );

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void showAsDropdown(View view, int x , int y , int gravity){
        mPopwindow.showAsDropDown(view ,x,y ,gravity );
    }

    public void close(){
        mPopwindow.dismiss();
    }
    public  interface OnClickCallBack{
        void OnClick();
    }
}

