package test.cn.sjz.testproject.wuliusystem.http.bean;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/27.
 * Explain
 */

public class RecordListBean implements Serializable {

    /**
     * time : 2019-04-26
     * num : 4
     */

    private String time = "";
    private int num;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
