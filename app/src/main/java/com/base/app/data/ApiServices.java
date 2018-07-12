package com.base.app.data;

import com.base.app.base.BaseList;
import com.base.app.base.BaseObj;
import com.base.app.model.CountryResponse;
import com.base.app.model.BaseValueItem;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.JobDetail;
import com.base.app.model.JobItem;
import com.base.app.model.LoginItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.RoleItem;
import com.base.app.model.joblasted.JobLasted;
import com.base.app.model.postobj.RegisterObj;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//{lang} default is vi
public interface ApiServices {

    @FormUrlEncoded
    @POST("api/vi/osin/login")
    Observable<BaseObj<LoginItem>> postLogin(@Field("phone") String Phone, @Field("password") String Password);

    //@FormUrlEncoded
    @POST("api/vi/osin/register")
    Observable<BaseObj<RegisterItem>> postRegister(@Body RegisterObj obj);


    @GET("api/vi/countries/list")
    Observable<BaseList<BaseValueItem>> getCountries();

    @FormUrlEncoded
    @POST("api/vi/osin/changePassword")
    Single<BaseObj> postChangePasss(@Field("osin_id") int osin_id, @Field("old_password") String old_password, @Field("new_password") String new_password, @Field("new_password_confirmation") String new_password_confirmation);

    @GET("api/vi/office/list")
    Observable<BaseList<BaseValueItem>> getOffices();

    @GET("api/vi/osin/roles")
    Observable<BaseList<RoleItem>> getRoles();

    @GET("api/vi/uploadSingleImage")
    Observable<BaseObj<Object>> uploadFile();

    //Info job

    @FormUrlEncoded
    @POST("api/vi/osin/job/cancel")
    Completable getMaidJobCancel(@Field("job_id") int job_id, @Field("sub_job_id") int sub_job_id, @Field("osin_id") int osin_id);

    @FormUrlEncoded
    @POST("api/vi/osin/job/detail")
    Observable<BaseObj<JobDetail>> getMaidJobDetail(@Field("job_id") int job_id, @Field("sub_job_id") int sub_job_id, @Field("osin_id") int osin_id);

    @FormUrlEncoded
    @POST("api/vi/osin/job/current")
    Observable<BaseList<JobCurrentItem>> getMaidJobCurrent(@Field("osin_id") int osin_id);

    @FormUrlEncoded
    @POST("api/vi/osin/job/listLastedJob")
    Observable<BaseObj<JobLasted>> getMaidJobLasted(@Field("osin_id") int osin_id, @Field("limit") int limit, @Field("mode") int mode);

    @FormUrlEncoded
    @POST("api/vi/osin/job/history")
    Observable<BaseList<JobCurrentItem>> getMaidJobHistory(@Field("osin_id") int osin_id, @Field("status") int status);

    @FormUrlEncoded
    @POST("api/vi/osin/job/register")
    Completable getMaidJobRegister(@Field("job_id") int job_id,
                                   @Field("sub_job_id") int sub_job_id,
                                   @Field("osin_id") int osin_id);

    //JOB
    @FormUrlEncoded
    @POST("api/vi/job/getLastedJob")
    Observable<BaseList<JobItem>> getJobLast();


    @FormUrlEncoded
    @POST("api/vi/job/register")
    Observable<BaseObj<JobDetail>> postJobRegister(@Field("name") String name,
                                                   @Field("description") String description,
                                                   @Field("rank") int rank,
                                                   @Field("tool") int tool,
                                                   @Field("lat") double latidute,
                                                   @Field("long") double longtidute);

}
