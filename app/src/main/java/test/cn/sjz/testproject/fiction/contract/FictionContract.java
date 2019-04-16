package test.cn.sjz.testproject.fiction.contract;

import test.cn.sjz.testproject.base.baseview.IPresenterBase;
import test.cn.sjz.testproject.base.baseview.IViewBase;

/**
 * Created by lwd on 2019/4/1.
 */

public interface FictionContract {
     interface IPresenter extends IPresenterBase {
        void getIndex();
    }
     interface FictionIView extends IViewBase<IPresenter>{
         void showIndex(String s);
    }
}
