package test.cn.sjz.testproject.testsystem.http;

import android.app.Activity;
import android.os.Handler;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.MediaType;
import test.cn.sjz.testproject.testsystem.http.Api;
import test.cn.sjz.testproject.testsystem.http.callback.AddNoteCallBack;
import test.cn.sjz.testproject.testsystem.http.callback.GetCountCallBack;
import test.cn.sjz.testproject.testsystem.http.callback.GetDetailCallBack;
import test.cn.sjz.testproject.testsystem.http.callback.GetListCallBack;
import test.cn.sjz.testproject.testsystem.http.callback.GetTypeCallBack;
import test.cn.sjz.testproject.testsystem.http.callback.LoginCallBack;
import test.cn.sjz.testproject.testsystem.http.requestbody.GetListBody;
import test.cn.sjz.testproject.testsystem.http.requestbody.LoginBody;

/**
 * Explain 接口请求类
 */

public class HttpManager {
    private Activity activity;
    private Handler handler;
    public HttpManager(Activity activity, Handler handler) {
        super();
        this.activity = activity;
        this.handler = handler;
    }
    public void login(LoginBody body){
        String json=new Gson().toJson(body);
        OkHttpUtils
                .postString()
                .url(Api.url_login)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new LoginCallBack(activity, handler));

    }

    public void addnote(LoginBody body){
        String json=new Gson().toJson(body);
        OkHttpUtils
                .postString()
                .url(Api.url_add_note)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new AddNoteCallBack(activity, handler));

    }

    public void getlist(GetListBody body){
        String json=new Gson().toJson(body);
        OkHttpUtils
                .postString()
                .url(Api.url_get_list)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new GetListCallBack(activity, handler));

    }

    public void gettype(){
        OkHttpUtils
                .get()
                .url(Api.url_get_note_type)
                .build()
                .execute(new GetTypeCallBack(activity, handler));

    }

    public void getdetail(int id){
        String url = Api.url_get_list + String.valueOf(id);
        OkHttpUtils
                .get()
                .url(Api.url)
                .build()
                .execute(new GetDetailCallBack(activity, handler));

    }

    public void getcount(int uid){
        String url = Api.url_get_count + String.valueOf(uid);
        OkHttpUtils
                .get()
                .url(Api.url)
                .build()
                .execute(new GetCountCallBack(activity, handler));
    }

}
