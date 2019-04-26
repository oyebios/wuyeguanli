package test.cn.sjz.testproject.testsystem.http.requestbody;

import java.io.Serializable;



public class LoginBody implements Serializable {

    public LoginBody(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * name : string
     * password : string
     */

    public String name;
    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
