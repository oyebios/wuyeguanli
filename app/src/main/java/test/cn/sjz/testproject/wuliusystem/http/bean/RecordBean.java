package test.cn.sjz.testproject.wuliusystem.http.bean;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/27.
 * Explain
 */

public class RecordBean implements Serializable {

    /**
     * id : 5
     * uid : 3
     * name : 李四
     * noteTypeId : 1
     * type : 1
     * content : 2019-4-26，今天天气很好
     * treatment : 你觉得呢
     * reviewStatus : null
     * reviewContent : null
     * longitude : 120.12951
     * latitude : 30.27161
     * createTime : 1556282950843
     * timeStr : 2019-04-26
     */

    public int id;
    public int uid;
    public String name="";
    public int noteTypeId;
    public int type;
    public String content="";
    public String treatment="";
    public double longitude;
    public double latitude;
    public long createTime;
    public String timeStr = "";
    public String reviewContent = "";
    public int reviewStatus ;

}
