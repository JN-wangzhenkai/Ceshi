package com.pdd.ceshi.raw;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ImageApi {

    @POST("upload")
    @FormUrlEncoded
    Call<ResponseBody> upLOadImage(@Field("mobile") String mobile,
                                   @Field("file")File file
    );



    @GET()
    Call<ResponseBody>up(@Query("mobile")String mobile,
                         @Query("file")File file
                         );


    @Multipart
    @POST("upload")
    Call<ResponseBody>upLoadImage(@PartMap Map<String,RequestBody> params);
}
