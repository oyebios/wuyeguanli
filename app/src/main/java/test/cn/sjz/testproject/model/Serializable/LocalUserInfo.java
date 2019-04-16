package test.cn.sjz.testproject.model.Serializable;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/16.
 */

public class LocalUserInfo implements Serializable{
    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
