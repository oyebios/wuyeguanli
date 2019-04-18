package test.cn.sjz.testproject.fiction.presenter;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import test.cn.sjz.testproject.Net.HttpLoader.FictonHttpLoader;
import test.cn.sjz.testproject.fiction.contract.FictionContract;

/**
 * Created by lwd on 2019/4/1.
 */

public class FictionIPresenter implements FictionContract.IPresenter {
    FictionContract.FictionIView view;

    public FictionIPresenter(FictionContract.FictionIView view){
        this.view = view ;
        view.setPresenter(this);
    }
    @Override
    public void getIndex() {
//        Observable<String> observable = new FictonHttpLoader().getIndex();
//        observable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                view.showIndex(s);
//            }
//        });
    }
}
