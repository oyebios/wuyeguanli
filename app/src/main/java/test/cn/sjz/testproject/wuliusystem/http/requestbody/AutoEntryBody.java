package test.cn.sjz.testproject.wuliusystem.http.requestbody;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/26.
 * Explain
 */

public class AutoEntryBody implements Serializable {
    public AutoEntryBody(String code) {
        this.code = code;
    }

    public String code;
}
