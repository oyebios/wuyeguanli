package test.cn.sjz.testproject.testsystem.http.callback;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import test.cn.sjz.testproject.testsystem.http.Api;
import test.cn.sjz.testproject.testsystem.http.bean.TypeBean;

/**
 * Explain 获取类型回调
 */

public class GetTypeCallBack extends StringCallback {
    private Context context;
    private Handler handler;

    public GetTypeCallBack(Context context, Handler handler) {
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

                    if (jsonObject.has("data")&&jsonObject.getJSONObject("data")!=null){
                        msg.what = Api.GET_TYPE_S;
                        List<TypeBean> datalist = new ArrayList<>();
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0;i<data.length();i++){
                            TypeBean item = new Gson().fromJson(data.getJSONObject(i).toString(),TypeBean.class);
                            datalist.add(item);
                        }
                        msg.obj = datalist;
                    }else {
                        if (jsonObject.has("message")) {
                            errorString = jsonObject.getString("message");
                            msg.what = Api.GET_TYPE_F;
                            msg.obj = errorString;
                        }
                    }
                } else {
                    if (jsonObject.has("message")) {
                        errorString = jsonObject.getString("message");
                        msg.what = Api.GET_TYPE_F;
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
