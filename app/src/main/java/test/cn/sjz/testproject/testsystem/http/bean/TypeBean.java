package test.cn.sjz.testproject.testsystem.http.bean;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/26.
 * Explain
 */

public class TypeBean implements Serializable {

    /**
     * id : 1
     * typeName : 基础设施
     */

    private int id;
    private String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
