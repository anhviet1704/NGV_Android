package com.base.app.automap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlacesClient {
    @GET("autocomplete/json?components=country:VN")
    Observable<AddressResponse> autocomplete(@Query("input") String str);

    @GET("details/json")
    Observable<DetailResponse> details(@Query("placeid") String placeId);
}
