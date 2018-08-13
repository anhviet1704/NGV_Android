package com.base.app.data;

import com.base.app.base.BaseList;
import com.base.app.base.BaseObj;
import com.base.app.model.BaseValueItem;
import com.base.app.model.CategoryItem;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.JobDetail;
import com.base.app.model.JobItem;
import com.base.app.model.LoginItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.UploadItem;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.model.postobj.RegisterObj;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

//{lang} default is vi
public interface ApiServices {

    @FormUrlEncoded
    @POST("api/{lang}/osin/login")
    Observable<BaseObj<LoginItem>> postLogin(@Path("lang") String lang, @Field("phone") String Phone, @Field("password") String Password);

    @FormUrlEncoded
    @POST("api/{lang}/osin/register")
    Observable<BaseObj<RegisterItem>> postRegister(@Path("lang") String lang,
                                                   @Field("phone") String phone,
                                                   @Field("password") String password,
                                                   @Field("address") String address,
                                                   @Field("fullname") String fullname,
                                                   @Field("lat") double lat,
                                                   @Field("lon") double lon,
                                                   @Field("birthday") String birthday,
                                                   @Field("country") String country,
                                                   @Field("email") String email,
                                                   @Field("gender") int gender,
                                                   @Field("identity_img") String identity_img,
                                                   @Field("office") String office,
                                                   @Field("status") int status,
                                                   @Field("job_id") String job_id
    );


    @GET("api/{lang}/countries/list")
    Observable<BaseList<BaseValueItem>> getCountries(@Path("lang") String lang);

    @FormUrlEncoded
    @POST("api/{lang}/osin/changePassword")
    Single<BaseObj> postChangePasss(@Path("lang") String lang, @Field("osin_id") int osin_id, @Field("old_password") String old_password, @Field("new_password") String new_password, @Field("new_password_confirmation") String new_password_confirmation);

    @FormUrlEncoded
    @POST("api/{lang}/osin/forgotPassword")
    Single<BaseObj> onForgotPassword(@Path("lang") String lang, @Field("osin_id") String osin_id, @Field("password") String password);

    @GET("api/{lang}/office/list")
    Observable<BaseList<BaseValueItem>> getOffices(@Path("lang") String lang);

    @GET("api/{lang}/osin/job/get-list-category")
    Observable<BaseList<CategoryItem>> getRoles(@Path("lang") String lang);

    //Info job

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/cancel")
    Completable getMaidJobCancel(@Path("lang") String lang, @Field("owner_job_id") int owner_job_id, @Field("osin_id") int osin_id);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/complete")
    Completable getMaidJobFinish(@Path("lang") String lang, @Field("owner_job_id") int owner_job_id, @Field("osin_id") int osin_id);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/detail")
    Observable<BaseObj<JobDetail>> getMaidJobDetail(@Path("lang") String lang, @Field("owner_job_id") int owner_job_id, @Field("osin_id") int osin_id);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/current")
    Observable<BaseList<JobCurrentItem>> getMaidJobCurrent(@Path("lang") String lang, @Field("osin_id") int osin_id);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/listLastedJob")
    Observable<BaseObj<JobNewResponse>> getMaidJobLasted(@Path("lang") String lang, @Field("osin_id") int osin_id, @Field("limit") int limit, @Field("mode") int mode, @Query("page") int page);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/search")
    Observable<BaseObj<JobNewResponse>> getMaidJobSearch(@Path("lang") String lang, @Field("osin_id") int osin_id, @Field("limit") int limit, @Query("text") String text);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/history")
    Observable<BaseList<JobCurrentItem>> getMaidJobHistory(@Path("lang") String lang, @Field("osin_id") int osin_id, @Field("status") int status);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/register")
    Completable getMaidJobRegister(@Path("lang") String lang, @Field("owner_job_id") int owner_job_id,
                                   @Field("osin_id") int osin_id,
                                   @Field("deal") String deal);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/rate")
    Single<BaseObj> maidRateJob(@Path("lang") String lang, @Field("osin_id") int osin_id, @Field("owner_job_id") int owner_job_id, @Field("rate_job") int rate_job);

    //JOB
    @FormUrlEncoded
    @POST("api/{lang}/job/getLastedJob")
    Observable<BaseList<JobItem>> getJobLast(@Path("lang") String lang);


    @FormUrlEncoded
    @POST("api/{lang}/job/register")
    Observable<BaseObj<JobDetail>> postJobRegister(@Path("lang") String lang, @Field("name") String name,
                                                   @Field("description") String description,
                                                   @Field("rank") int rank,
                                                   @Field("tool") int tool,
                                                   @Field("lat") double latidute,
                                                   @Field("long") double longtidute);

    @FormUrlEncoded
    @POST("api/{lang}/osin/job/listLatLongJob")
    Observable<BaseObj<JobNewResponse>> getMaidJobLastedMap(@Path("lang") String lang, @Field("osin_id") int osin_id,
                                                            @Field("latitude") double latitude,
                                                            @Field("longitude") double longitude,
                                                            @Field("radius") float radius,
                                                            @Field("mode") int mode,
                                                            @Field("limit") int limit);

    @Multipart
    @POST("api/{lang}/osin/uploadSingleImage")
    Single<BaseObj<UploadItem>> uploadFile(@Path("lang") String lang, @Part MultipartBody.Part image);

}
