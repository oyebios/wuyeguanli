package test.cn.sjz.testproject.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lwd on 2019/3/15.
 * 创建请求
 */


public class RequestBody {
    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NeedList{

    }

    private JSONObject data = new JSONObject();

    @NeedList
    public RequestBody addParams(String name, Object value) {
        try {
            if(value instanceof ArrayList){
                JSONArray jsonArray = new JSONArray();
                for (int i=0;i<((ArrayList) value).size();i++)
                    jsonArray.put(((ArrayList) value).get(i));
                data.put(name,jsonArray);
                return this;
            }
            //需要sdk版本高于19
//            if(value instanceof ArrayList){
//                JSONArray jsonArray = new JSONArray(((ArrayList)value).toArray());
//                data.put(name,jsonArray);
//                return this;
//            }


            data.put(name,value);

        }catch (JSONException e){
            Log.e("JsonException",e.toString());
        }
        return this;
    }

    public String getDataString(){
        return data.toString();
    }

    public static String createJsonoString(Map<String,String> map){
        String jsondataString="";
        try {
            JSONObject jsonObject= new JSONObject(map);
            jsondataString = jsonObject.toString();
        }catch (Exception e){
            Log.e("JsonException",e.toString());
        }
        return jsondataString;
    }
}
