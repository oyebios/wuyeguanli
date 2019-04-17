package test.cn.sjz.testproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import rx.functions.Action;
import rx.functions.Action1;
import test.cn.sjz.testproject.Login.contract.IContractLogin;
import test.cn.sjz.testproject.Login.presenter.IPresenterLogin;
import test.cn.sjz.testproject.Net.HttpService;
import test.cn.sjz.testproject.Net.RetrofitFactory;
import test.cn.sjz.testproject.Utils.FileChooseUtil;
import test.cn.sjz.testproject.base.ARouterPath;
import test.cn.sjz.testproject.model.BoxTypeCapBean;
import test.cn.sjz.testproject.model.Parcelable.TestData;
import test.cn.sjz.testproject.ui.ui.MenuPopwindow;
import test.cn.sjz.testproject.ui.adapter.ZeroCapabilityAdapter;
import test.cn.sjz.testproject.ui.ui.RadioGroupLayout;

@Route(path = ARouterPath.ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {
    boolean isfirst=true;
    String name ;
    ListView listView;
    List<BoxTypeCapBean> listData = new ArrayList<>();
    ZeroCapabilityAdapter madapter;
    TextView tv ;
    EditText et;
    private MenuPopwindow utils;
    RadioGroupLayout radioGroupLayout;

    public  static final  int REQUEST_CHOOSEFILE = 222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv_1);
        et= (EditText)findViewById(R.id.et_1);
        String strTest = "";
        ImageView imageView = new ImageView(this);

    }

//    public EventBus
    public void test(View v){
//        ARouter.getInstance().build(ARouterPath.ACTIVITY_ASCII).navigation(this,1);
//            ViewOutlineProvider outlineProvider = new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//                outline.setOval(0,0,view.getWidth(),view.getHeight());
//            }
//        };
//        RelativeLayout rl_1 = (RelativeLayout)findViewById(R.id.rl_1);
//        rl_1.setOutlineProvider(outlineProvider);
//        rl_1.setClipToOutline(true);
//
//        ViewOutlineProvider outlineProvider2 = new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//                outline.setOval(0,0,view.getWidth(),view.getHeight());
//            }
//        };
//        RelativeLayout rl_2 = (RelativeLayout)findViewById(R.id.rl_2);
//
//        rl_2.setOutlineProvider(outlineProvider);
//        rl_2.setClipToOutline(true);

//        utils.showAsDropdown(tv);
//        iPresnter.LoginIn("hah ");
//        name = et.getText().toString();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");//选择图片
        //intent.setType(“audio/*”); //选择音频
        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
        //intent.setType(“video/*;image/*”);//同时选择视频和图片
        intent.setType("*/*");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CHOOSEFILE);


    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){//选择文件返回
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
        switch(requestCode){
            case REQUEST_CHOOSEFILE:
              Uri uri=data.getData();
               String chooseFilePath= FileChooseUtil.getInstance(this).getChooseFileResultPath(uri);
              Log.d("testfile choose","选择文件返回："+chooseFilePath);
               break;
            case 1:
                TestData data1 = data.getParcelableExtra("data");
                int i = 0;
                break;
        }
    }
}











    public void testRxjava(){
        Observer<String> observer = new Observer<String>() { // 第三步：订阅

            // 第二步：初始化Observer
            private int I;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull String integer) {
                tv.setText(integer);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        };
        Observable.create(new ObservableOnSubscribe<String>() { // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {

                e.onNext(name);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }



    private void testListView() {
//        listView = (ListView)findViewById(R.id.lv_1);
        listData.add(getBean(2));
        listData.add(getBean(3));
        listData.add(getBean(4));
        listData.add(getBean(5));
        listData.add(getBean(6));
        listData.add(getBean(7));
        listData.add(getBean(8));
        listData.add(getBean(2));

        madapter = new ZeroCapabilityAdapter(this, listData, new ZeroCapabilityAdapter.OnCheckedListener() {
            @Override
            public void onChecked(int position, int selectedIndex) {
                listData.get(position).selectedIndex = selectedIndex;
            }
        });
        listView.setAdapter(madapter);

    }

    private BoxTypeCapBean getBean(int i) {
        List<Integer> list = new ArrayList<Integer>() {};
        for (int k=0;k<i;k++){
            list.add( (k+1)*100);
        }
        BoxTypeCapBean bean = new BoxTypeCapBean("06#", list, "10*10*10");
        return bean;
    }


}
