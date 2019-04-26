package test.cn.sjz.testproject.testsystem.http.callback;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import test.cn.sjz.testproject.testsystem.http.Api;

/**
 * Explain回调  添加巡查记录
 */

public class AddNoteCallBack extends StringCallback {
    private Context context;
    private Handler handler;

    public AddNoteCallBack(Context context, Handler handler) {
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
