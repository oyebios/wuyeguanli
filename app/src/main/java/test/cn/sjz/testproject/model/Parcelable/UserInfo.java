package test.cn.sjz.testproject.model.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lwd on 2019/4/16.
 */

public class UserInfo implements Parcelable {

    private String userName;
    private String passWord;


    public UserInfo(){

    }
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

    protected UserInfo(Parcel in) {
        userName = in.readString();
        passWord = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(passWord);
    }
}
