package test.cn.sjz.testproject.testsystem.http;



public class Api {
    public static String url="http://118.25.87.131:9900";
    //登录
    public static final String url_login=url+"/app/login";
    //添加巡检信息
    public static final String url_add_note=url+"/note/add-note";
    //获取所有类型
    public static final String url_get_note_type=url+"/note/all-note-type";
    //获取具体信息
    public static final String url_get_detail=url+"/note/get-note-info/";

    //获取记录
    public static final String url_get_list=url+"/note/get-today-notes";

    //获取统计信息
    public static final String url_get_count =url+"/note/note-count-info/";

    public static final int FAILED=0;

    public static final int LOGIN_SUCCESS = 2000;
    public static final int LOGIN_FAILED=2001;

    public static final int ADD_NOTE_S = 2002;
    public static final int ADD_NOTE_F=2003;

    public static final int GET_TYPE_S = 2004;
    public static final int GET_TYPE_F=2005;

    public static final int GET_DETAIL_S = 2006;
    public static final int GET_DETAIL_F=2007;

    public static final int GET_LIST_S = 2008;
    public static final int GET_LIST_F=2009;

    public static final int GET_COUNT_S = 2010;
    public static final int GET_COUNT_F=2011;

}
