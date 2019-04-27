package test.cn.sjz.testproject.wuliusystem.http.requestbody;

import java.io.Serializable;


public class AddTrackBody implements Serializable {


    /**
     * location : string
     * logisticsId : 0
     * status : 0
     */

    private String location;
    private int logisticsId;
    private int status;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(int logisticsId) {
        this.logisticsId = logisticsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
