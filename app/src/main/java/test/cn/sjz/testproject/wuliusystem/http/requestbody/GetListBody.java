package test.cn.sjz.testproject.wuliusystem.http.requestbody;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/26.
 * Explain
 */

public class GetListBody implements Serializable {

    public GetListBody(String time, int uid) {
        this.time = time;
        this.uid = uid;
    }

    /**
     * time : string
     * uid : 0
     */


    public String time;
    public int uid;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
