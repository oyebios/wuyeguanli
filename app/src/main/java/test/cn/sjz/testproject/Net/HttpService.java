package test.cn.sjz.testproject.Net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lwd on 2019/3/29.
 */

public interface HttpService {
    //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<String> getTop250(@Query("start") int start, @Query("count") int count);

//    @FormUrlEncoded
//    @POST("/x3/weather")
//    Call<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);
}
