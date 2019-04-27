package test.cn.sjz.testproject.testsystem.http.bean;

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

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getNoteTypeId() {
//        return noteTypeId;
//    }
//
//    public void setNoteTypeId(int noteTypeId) {
//        this.noteTypeId = noteTypeId;
//    }
//
//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getTreatment() {
//        return treatment;
//    }
//
//    public void setTreatment(String treatment) {
//        this.treatment = treatment;
//    }
//
//    public Object getReviewStatus() {
//        return reviewStatus;
//    }
//
//    public void setReviewStatus(Object reviewStatus) {
//        this.reviewStatus = reviewStatus;
//    }
//
//    public Object getReviewContent() {
//        return reviewContent;
//    }
//
//    public void setReviewContent(Object reviewContent) {
//        this.reviewContent = reviewContent;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public long getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(long createTime) {
//        this.createTime = createTime;
//    }
//
//    public String getTimeStr() {
//        return timeStr;
//    }
//
//    public void setTimeStr(String timeStr) {
//        this.timeStr = timeStr;
//    }
}
