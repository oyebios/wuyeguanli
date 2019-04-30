package test.cn.sjz.testproject.Net.HttpLoader;

import io.reactivex.Observable;
import retrofit2.http.GET;
import test.cn.sjz.testproject.Net.RetrofitFactory;
import test.cn.sjz.testproject.base.BaseHttpLoader;



public class FictonHttpLoader extends BaseHttpLoader{
    FictionService service ;
    public FictonHttpLoader(){
        service = RetrofitFactory.getInstance().create(FictionService.class);
    }
    public Observable<String> getIndex(){
        return subscribe(service.getIndex());
    }
    interface FictionService{
        @GET("/get")
        Observable<String> getIndex();
    }
}
