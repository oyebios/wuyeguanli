package test.cn.sjz.testproject.wuliusystem.http;

import android.app.Activity;
import android.os.Handler;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.MediaType;
import test.cn.sjz.testproject.wuliusystem.http.callback.AddTrackInfoCallBack;
import test.cn.sjz.testproject.wuliusystem.http.callback.AutoEntryCallBack;
import test.cn.sjz.testproject.wuliusystem.http.callback.GetTrackInfoCallBack;
import test.cn.sjz.testproject.wuliusystem.http.callback.HandEntryCallBack;
import test.cn.sjz.testproject.wuliusystem.http.callback.LoginCallBack;
import test.cn.sjz.testproject.wuliusystem.http.requestbody.AddTrackBody;
import test.cn.sjz.testproject.wuliusystem.http.requestbody.AutoEntryBody;
import test.cn.sjz.testproject.wuliusystem.http.requestbody.HandEntryBody;
import test.cn.sjz.testproject.wuliusystem.http.requestbody.LoginBody;

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

    public void addtrack(AddTrackBody body){
        String json=new Gson().toJson(body);
        OkHttpUtils
                .postString()
                .url(Api.url_add_track_info)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new AddTrackInfoCallBack(activity, handler));

    }

    public void autoentry(AutoEntryBody body){
        String json=new Gson().toJson(body);
        OkHttpUtils
                .postString()
                .url(Api.url_auto_entry)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new AutoEntryCallBack(activity, handler));

    }

    public void gettrack(String code){
        String url = Api.url_get_track_info + code;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new GetTrackInfoCallBack(activity, handler));

    }

    public void handentry(HandEntryBody body){
        String json=new Gson().toJson(body);
        OkHttpUtils
                .postString()
                .url(Api.url_manual_entry)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .build()
                .execute(new HandEntryCallBack(activity, handler));

    }


}
