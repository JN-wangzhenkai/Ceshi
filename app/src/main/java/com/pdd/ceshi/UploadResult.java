package com.pdd.ceshi;

import com.google.gson.annotations.SerializedName;

public class UploadResult {


    @SerializedName("error")
    private String msg;

    @SerializedName("urlcount")
    private int count;

    @SerializedName("smallImgUrl")
    private String url;

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return url;
    }

    public String getMsg() {
        return msg;
    }

}
