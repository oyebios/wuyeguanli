package test.cn.sjz.testproject.wuliusystem.http.callback;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import test.cn.sjz.testproject.wuliusystem.http.Api;

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
        Log.d("test", response);
        String errorString = null;
        if (response != null) {
            try {
                Message msg = new Message();
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.has("success") && jsonObject.getBoolean("success") ) {
                    msg.what = Api.ADD_NOTE_S;

                } else {
                    if (jsonObject.has("message")) {
                        errorString = jsonObject.getString("message");
                        msg.what = Api.ADD_NOTE_F;
                        msg.obj = errorString;
                    }
                }
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
