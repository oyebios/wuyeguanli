package test.cn.sjz.testproject.wuliusystem.http.callback;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import test.cn.sjz.testproject.wuliusystem.http.Api;

/**
 * Explain 获取详情
 */

public class GetDetailCallBack extends StringCallback {
    private Context context;
    private Handler handler;

    public GetDetailCallBack(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        Message msg = new Message();
        msg.what = Api.FAILED;
        //msg.obj = e.toString();
        msg.obj = "error";
        handler.sendMessage(msg);
    }

    @Override
    public void onResponse(String response, int id) {

    }
}
