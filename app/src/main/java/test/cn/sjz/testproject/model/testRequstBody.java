package test.cn.sjz.testproject.model;

import java.util.List;

/**
 * Created by lwd on 2019/3/21.
 */

public class testRequstBody {

    /**
     * interface : capability_register
     * sessionId : …
     * userId : 10
     * onDutyTime : 8:00
     * offDutyTime : 23:00
     * sourceLngAndLat : 120.250991,30.231815
     * sourceUserInputAddress : …
     * sourceAddress : 45646
     * sourceAddressDetail : …
     * sourceProvince : 7897
     * sourceCity : …
     * sourceArea : …
     * name : …
     * capabilityName : ...
     * phoneNumber : …
     * boxCapabilities : [{"specsType":"06#","count":100},{"specsType":"10#","count":500}]
     */
//
//    public String interfaceX;
//    public String sessionId;
//    public int userId;
//    public String onDutyTime;
//    public String offDutyTime;
//    public String sourceLngAndLat;
//    public String sourceUserInputAddress;
//    public String sourceAddress;
//    public String sourceAddressDetail;
//    public String sourceProvince;
//    public String sourceCity;
//    public String sourceArea;
    public String name;
    public String capabilityName;
    public String phoneNumber;
    public List<BoxCapabilitiesBean> boxCapabilities;

    public void setBoxCapabilities(List<BoxCapabilitiesBean> boxCapabilities) {
        this.boxCapabilities = boxCapabilities;
    }

    public static class BoxCapabilitiesBean {
        /**
         * specsType : 06#
         * count : 100
         */

        private String specsType;
        private int count;

        public String getSpecsType() {
            return specsType;
        }

        public void setSpecsType(String specsType) {
            this.specsType = specsType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
