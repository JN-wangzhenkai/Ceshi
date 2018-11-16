package com.pdd.ceshi.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class getUrlAns {

    public static String doPost(List<NameValuePair> params, String url) throws Exception {

        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            httpPost.setEntity(entity);
        }

        HttpResponse httpResp = httpClient.execute(httpPost);
        if (httpResp.getStatusLine().getStatusCode() == 200) {
            result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
        } else {


        }

        return result;
    }

}
