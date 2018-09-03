package com.pdd.ceshi.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpIntercepter  implements Interceptor{

    String md5 = "";
    List<String> list = new ArrayList<String>();

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request request = chain.request();
        FormBody oldFormBody = (FormBody) request.body();

        for (int i = 0; i < oldFormBody.size(); i++) {
            list.add(oldFormBody.encodedValue(i));
        }

        String str = addCommaStr(list);
        //字符串转成字符串数组
        String[] arr = str.split(",");

        //调用工具类进行排序
        String[] keys = StringSortUtil.getUrlParam(arr);

        //把字符串数组转换成字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < keys.length; i++) {
            sb.append(keys[i]);
        }
        String result = sb.toString();

        String ss_Token = result + "pddwangluoyonghutoken2018";

        md5 = MD5Utils.calc(ss_Token);

        String oldurl = request.url().toString();
        Request newRequest = addParam(request);

//        HttpUrl originalHttpUrl = request.url();
//        Log.d("0000000000", "intercept: " + originalHttpUrl);
//        HttpUrl url = originalHttpUrl.newBuilder()
//                .addQueryParameter("token", md5)
//                .build();
//
//        Request.Builder requestBuilder = request.newBuilder()
//                .url(url);
//
//        Request request1 = requestBuilder.build();

        return chain.proceed(newRequest);
    }

    /**
     * 添加公共参数
     */
    private Request addParam(Request oldRequest) {
        HttpUrl.Builder builder = oldRequest.url()
                .newBuilder()
                .setEncodedQueryParameter("token", md5);

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build();
        return newRequest;
    }

    /**
     * 加逗号
     *
     * @param
     * @return
     */
    private String addCommaStr(List<String> list) {
        String str = "";
        StringBuffer stringBuffer = new StringBuffer();

        if (list.size() < 2) {
            str = stringBuffer.append(list.get(0)).toString();

        } else {
            for (int i = 0; i < list.size(); i++) {

                stringBuffer.append(list.get(i) + ",");

            }
            str = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1).trim();
        }


        return str;
    }
}
