package test.cn.sjz.testproject.wuliusystem.http.callback;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import test.cn.sjz.testproject.wuliusystem.http.Api;
import test.cn.sjz.testproject.wuliusystem.http.bean.UserBean;

/**
 *
 * Explain回调  登录
 */

public class LoginCallBack extends StringCallback {
    private Context context;
    private Handler handler;

    public LoginCallBack(Context context, Handler handler) {
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
                    msg.what = Api.LOGIN_SUCCESS;
                    UserBean userBean = new Gson().fromJson(jsonObject.getString("data"),UserBean.class);
                    msg.obj =userBean;

                } else {
                    if (jsonObject.has("message")) {
                        errorString = jsonObject.getString("message");
                        msg.what = Api.LOGIN_FAILED;
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
