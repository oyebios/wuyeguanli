package test.cn.sjz.testproject.wuliusystem.http.bean;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/27.
 * Explain
 */

public class UserBean implements Serializable {

    /**
     * id : 3
     * name : 李四
     * email : sss@163.com
     * password : null
     * headImage : null
     * role : 2
     * createTime : 1555642661806
     */

    private int id;
    private String name = "";
    private String email = "";
    private String password ="";
    private String headImage="";
    private int role;
    private long createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
