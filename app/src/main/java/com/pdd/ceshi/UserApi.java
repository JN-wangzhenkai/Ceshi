package com.pdd.ceshi;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserApi {


    // 多部分（多个请求头和请求体）
    // 多部分（多个请求头和请求体）
    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part MultipartBody.Part part); // 上传头像接口



}
