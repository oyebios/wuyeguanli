package test.cn.sjz.testproject.Login.contract;

import test.cn.sjz.testproject.base.IPresenterBase;
import test.cn.sjz.testproject.base.IViewBase;

/**
 * Created by lwd on 2019/3/14.
 */

public interface IContractLogin {
     interface IPresnter extends IPresenterBase{
         void LoginIn(String name,String password);
    }
     interface IViewLogin extends IViewBase<IPresnter>{

         void showLoginSuccess(String s);
    }
}
