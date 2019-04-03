package test.cn.sjz.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import test.cn.sjz.testproject.model.BoxTypeCapBean;
import test.cn.sjz.testproject.ui.ui.MenuPopwindow;
import test.cn.sjz.testproject.ui.adapter.ZeroCapabilityAdapter;
import test.cn.sjz.testproject.ui.ui.RadioGroupLayout;

public class MainActivity extends AppCompatActivity implements IContractLogin.IViewLogin{
    IContractLogin.IPresnter iPresnter;
    boolean isfirst=true;
    String name ;
    ListView listView;
    List<BoxTypeCapBean> listData = new ArrayList<>();
    ZeroCapabilityAdapter madapter;
    TextView tv ;
    EditText et;
    private MenuPopwindow utils;
    RadioGroupLayout radioGroupLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresnter = new IPresenterLogin(this);
        tv = (TextView) findViewById(R.id.tv_1);
        et= (EditText)findViewById(R.id.et_1);
        String strTest = "";
//       testListView();
//        testRxjava();
//        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        long [] patten = {100,200,100,200,100,500};
//        vibrator.vibrate(patten,3);
        String x = " ";

        utils = new MenuPopwindow(MainActivity.this,getLayoutInflater().inflate(R.layout.popwindow_loginout,null))
                .setTvText(R.id.tv_loginout,"用户:李伟东")
                .setFocusable(true)
                .setBackground(getResources().getDrawable(R.drawable.zero_capability_shape_selected))
                .setOutsideTouchable(true)
                .setClickListener(R.id.btn_out, true,new MenuPopwindow.OnClickCallBack() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(MainActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void test(View v){

//        utils.showAsDropdown(tv);
//        iPresnter.LoginIn("hah ");
//        name = et.getText().toString();
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

    @Override
    public void showLoginSuccess(String s) {
        Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(IContractLogin.IPresnter iPresnter2) {
        iPresnter=iPresnter2;
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
