package test.cn.sjz.testproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lp on 2019/3/8.
 */

public class BoxTypeCapBean implements Serializable {

    public int selectedIndex = -1;

    public BoxTypeCapBean( String specsType, List<Integer> boxCapability, String boxSize) {
        this.specsType = specsType;
        this.boxCapability = boxCapability;
        this.boxSize = boxSize;
    }

    /**
     * specsId : 1

     * specsType : 06#
     * photoURL :
     * boxCapability : [100,200]
     * boxSize
     */

    private int specsId;
    private String specsType;
    private String photoURL;
    private List<Integer> boxCapability;
    private String boxSize = "";

    public String getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(String boxSize) {
        this.boxSize = boxSize;
    }

    public int getSpecsId() {
        return specsId;
    }

    public void setSpecsId(int specsId) {
        this.specsId = specsId;
    }

    public String getSpecsType() {
        return specsType;
    }

    public void setSpecsType(String specsType) {
        this.specsType = specsType;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public List<Integer> getBoxCapability() {
        return boxCapability;
    }

    public void setBoxCapability(List<Integer> boxCapability) {
        this.boxCapability = boxCapability;
    }

}
