package test.cn.sjz.testproject.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

import test.cn.sjz.testproject.base.MyApplication;

/**
 * Created by lwd on 2019/4/16.
 */

public class PreferUtil {

    private static final class PreferInstance{
        private static final PreferUtil INSTANCE = new PreferUtil();
    }
    public static PreferUtil getInstance(){
        return PreferInstance.INSTANCE;
    }

    private static SharedPreferences preferences;

    private PreferUtil(){
        preferences =  MyApplication.getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    }

    public static boolean putString(String name ,String str){
        return preferences.edit().putString(name,str).commit();
    }

    public static String getString(String name,String defaultVal){
        return preferences.getString(name,defaultVal);
    }
    public static boolean putInt(String name ,int str){
        return preferences.edit().putInt(name,str).commit();
    }

    public static Integer getInt(String name,int defaultVal){
        return preferences.getInt(name,defaultVal);
    }

    public static boolean putLong(String name ,long str){
        return preferences.edit().putLong(name,str).commit();
    }

    public static Long getLong(String name,long  defaultVal){
        return preferences.getLong(name,defaultVal);
    }
}
