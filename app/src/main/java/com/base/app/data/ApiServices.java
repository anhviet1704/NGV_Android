package com.base.app.data;

import com.base.app.base.BaseList;
import com.base.app.base.BaseObj;
import com.base.app.model.CountryResponse;
import com.base.app.model.BaseValueItem;
import com.base.app.model.LoginItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.RoleItem;
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
    Observable<BaseObj<CountryResponse>> getCountries();

    @FormUrlEncoded
    @POST("api/vi/osin/changePassword")
    Single<BaseObj> postChangePasss(@Field("id") String id, @Field("old_password") String old_password, @Field("new_password") String new_password, @Field("new_password_confirmation") String new_password_confirmation);

    @GET("api/vi/office/list")
    Observable<BaseList<BaseValueItem>> getOffices();

    @GET("api/vi/osin/roles")
    Observable<BaseList<RoleItem>> getRoles();


    @GET("api/vi/uploadSingleImage")
    Observable<BaseObj<Object>> uploadFile();

}
