package test.cn.sjz.testproject.testsystem.http.requestbody;

import java.io.Serializable;



public class AddNoteBody implements Serializable {

    /**
     * content : string
     * latitude : 0
     * longitude : 0
     * name : string
     * noteTypeId : 0
     * treatment : string
     * uid : 0
     */

    public String content;
    public double latitude;
    public double longitude;
    public String name;
    public int noteTypeId;
    public String treatment;
    public int uid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoteTypeId() {
        return noteTypeId;
    }

    public void setNoteTypeId(int noteTypeId) {
        this.noteTypeId = noteTypeId;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
