package test.cn.sjz.testproject.Login.presenter;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import test.cn.sjz.testproject.Login.contract.IContractLogin;
import test.cn.sjz.testproject.base.BaseHttpLoader;

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
    public void LoginIn(String name, String password) {
        view.showLoginSuccess("添加成功");
//        Observable<String> observable = new ().getMovies();
//        observable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                view.showLoginSuccess(s);
//            }
//        });
    }
}
