package test.cn.sjz.testproject.model.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lwd on 2019/4/16.
 */

public class TestData implements Parcelable {
    private UserInfo user;
    private ArrayList<String> listString = new ArrayList<>();
    private ArrayList<UserInfo> list = new ArrayList<>();

    public TestData(){}
    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public ArrayList<String> getListString() {
        return listString;
    }

    public void setListString(ArrayList<String> listString) {
        this.listString = listString;
    }

    public ArrayList<UserInfo> getList() {
        return list;
    }

    public void setList(ArrayList<UserInfo> list) {
        this.list = list;
    }

    protected TestData(Parcel in) {
        listString = in.createStringArrayList();
        // 读取对象需要提供一个类加载器去读取,因为写入的时候写入了类的相关信息
        user = in.readParcelable(UserInfo.class.getClassLoader());

        //读取集合也分为两类,对应写入的两类

        //这一类需要用相应的类加载器去获取
//        in.readList(list, UserInfo.class.getClassLoader());// 对应writeList


        //这一类需要使用类的CREATOR去获取
        in.readTypedList(list, UserInfo.CREATOR); //对应writeTypeList
    }

    public static final Creator<TestData> CREATOR = new Creator<TestData>() {
        @Override
        public TestData createFromParcel(Parcel in) {
            return new TestData(in);
        }

        @Override
        public TestData[] newArray(int size) {
            return new TestData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(listString);
        dest.writeParcelable(user,0);
        //这些方法们把类的信息和数据都写入Parcel，以使将来能使用合适的类装载器重新构造类的实例.所以效率不高
//        dest.writeList(list);


        //这些方法不会写入类的信息，取而代之的是：读取时必须能知道数据属于哪个类并传入正确的Parcelable.Creator来创建对象
        // 而不是直接构造新对象。（更加高效的读写单个Parcelable对象的方法是：
        // 直接调用Parcelable.writeToParcel()和Parcelable.Creator.createFromParcel()）

        dest.writeTypedList(list);
    }
}
