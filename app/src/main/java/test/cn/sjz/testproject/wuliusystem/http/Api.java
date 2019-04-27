package test.cn.sjz.testproject.wuliusystem.http;



public class Api {
    public static String url="http://118.25.87.131:8000";
    //登录
    public static final String url_login=url+"/user/login";
    //添加轨迹信息
    public static final String url_add_track_info=url+"/logistics/add-track-info";
    //获取自动录入
    public static final String url_auto_entry=url+"/logistics/auto-entry";
    //获取具体信息
    public static final String url_get_track_info=url+"/logistics/get-track-info/";

    //手动
    public static final String url_manual_entry=url+"/logistics/manual-entry";


    public static final int FAILED=0;

    public static final int LOGIN_SUCCESS = 3000;
    public static final int LOGIN_FAILED=3001;

    public static final int ADD_TRACK_S = 3002;
    public static final int ADD_TRACK_F=3003;

    public static final int AUTO_ENTRY_S = 3004;
    public static final int AUTO_ENTRY_F=3005;

    public static final int HAND_ENTRY_S = 3006;
    public static final int HAND_ENTRY_F=3007;

    public static final int GET_INFO_S = 3008;
    public static final int GET_INFO_F=3009;


}
