package test.cn.sjz.testproject.Login.presenter;

import android.preference.Preference;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import test.cn.sjz.testproject.Login.contract.IContractLogin;
import test.cn.sjz.testproject.Utils.PreferUtil;
import test.cn.sjz.testproject.base.BaseHttpLoader;
import test.cn.sjz.testproject.model.Parcelable.UserInfo;
import test.cn.sjz.testproject.model.Serializable.LocalUserInfo;

/**
 * Created by lwd on 2019/3/14.
 */

public class IPresenterLogin implements IContractLogin.IPresnter {
    IContractLogin.IViewLogin view;


    public IPresenterLogin(IContractLogin.IViewLogin view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void LoginIn(LocalUserInfo userInfo) {
        if (PreferUtil.getInstance().getString("userName","").equals("")){
            if (PreferUtil.getInstance().putString("passWord" , userInfo.getPassWord())&&PreferUtil.getInstance().putString("userName",userInfo.getUserName()))
                view.showLoginSuccess("注册成功");
            else view.showLoginFailed(false);
        }else {
            if (PreferUtil.getInstance().getString("passWord","").equals(userInfo.getPassWord())
                    &&PreferUtil.getInstance().getString("userName","").equals(userInfo.getUserName())){
                PreferUtil.getInstance().putInt("pwErrorTimes",0);
                view.showLoginSuccess("登录成功");
            }else {
                int time = PreferUtil.getInstance().getInt("pwErrorTimes",0);
                time += 1;
                PreferUtil.getInstance().putInt("pwErrorTimes",time);
                if (time >= 3){
                    view.showLoginFailed(true);
                }else {
                    view.showLoginFailed(false);
                }

            }
        }
    }
}
