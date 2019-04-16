package test.cn.sjz.testproject.Login.contract;

import test.cn.sjz.testproject.base.baseview.IPresenterBase;
import test.cn.sjz.testproject.base.baseview.IViewBase;
import test.cn.sjz.testproject.model.Parcelable.UserInfo;
import test.cn.sjz.testproject.model.Serializable.LocalUserInfo;

/**
 * Created by lwd on 2019/3/14.
 */

public interface IContractLogin {
     interface IPresnter extends IPresenterBase{
         void LoginIn(LocalUserInfo userInfo);
    }
     interface IViewLogin extends IViewBase<IPresnter>{

         void showLoginSuccess(String s);

         void showLoginFailed(boolean isShowReset);
    }
}
