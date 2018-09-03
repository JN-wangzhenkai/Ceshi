package com.pdd.ceshi;

import android.support.annotation.NonNull;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {

    @POST("userAddressQuery")
    @FormUrlEncoded
    Call<ResponseBody> hahah(@Field("userId") int userId,
                             @Field("theConsignee") String age,
                             @Field("phone") String phone,

                             @Field("areaId") int deee,

                             @Field("address") String defa,
                             @Field("isChoice") byte iscode

                             );

    @POST("goodsQuery")
    @FormUrlEncoded
    Call<ResponseBody>goods(@Field("pageNo")int pages,
                            @Field("pageSize")int pagesize,
                            @Field("shopId")String shop
    );




    /**
     * 发送验证码
     */

    @POST("codeSend")
    @FormUrlEncoded
    Call<ResponseBody>sendCode(@Field("mobile") String mobile);

    /**
     * 验证验证码是否正确
     */

    @POST("codeCheck")
    @FormUrlEncoded
    Call<ResponseBody>ifCodeTrue(@Field("mobile") String mobile,
                                 @Field("code") int code);

    @POST("expressQuery")
    @FormUrlEncoded
    Call<ResponseBody> getwuliu(@Field("shipperCode")String yd,
                                @Field("logisticCode") String num);

    /**
     * userId          Integer       必填       用户id
     pageNo      Integer     必填    当前页，默认值：1
     pageSize   Integer    必填    每页大小，默认值：10

     * @return
     */
    @POST("orderQueryPaging")
    @FormUrlEncoded
    Call<ResponseBody>orderQuery(
            @Field("userId")int userid,
            @Field("pageNo")int pageno,
            @Field("pageSize")int pagesize
    );



    @POST("orderCreate")
    @FormUrlEncoded
    Call<ResponseBody>orderCreate(@NonNull@Field("userId")int userId,
                                  @NonNull @Field("goodId") String goodsId,
                                  @NonNull@Field("shopId")  String shopId,
                                  @NonNull@Field("userAddressId") int usAddressId,

                                  @Field("quantity")int quantity);


    @POST("checkCode")
    @FormUrlEncoded
    Call<ResponseBody>yzm( @Field("params") String param);


   /**
           * 上传一张图片
         * @param description
         * @param imgs
         * @return
                 */
    @Multipart
    @POST("/upload")
    Call<String> uploadImage(@Part("fileName") String description,
                             @Part("file\"; filename=\"image.png\"") RequestBody imgs);

}
