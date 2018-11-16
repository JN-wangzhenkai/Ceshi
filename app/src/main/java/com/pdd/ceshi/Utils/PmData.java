package com.pdd.ceshi.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PmData {


    /**
     * resultcode : 200
     * reason : SUCCESSED!
     * result : [{"city":"??","PM2.5":"36","AQI":"62","quality":"?","PM10":"65","CO":"0.7","NO2":"15","O3":"164","SO2":"9","time":"2018-10-08 16:39:28"}]
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * city : ??
         * PM2.5 : 36
         * AQI : 62
         * quality : ?
         * PM10 : 65
         * CO : 0.7
         * NO2 : 15
         * O3 : 164
         * SO2 : 9
         * time : 2018-10-08 16:39:28
         */

        private String city;
        @SerializedName("PM2.5")
        private String _$PM2568; // FIXME check this code
        private String AQI;
        private String quality;
        private String PM10;
        private String CO;
        private String NO2;
        private String O3;
        private String SO2;
        private String time;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String get_$PM2568() {
            return _$PM2568;
        }

        public void set_$PM2568(String _$PM2568) {
            this._$PM2568 = _$PM2568;
        }

        public String getAQI() {
            return AQI;
        }

        public void setAQI(String AQI) {
            this.AQI = AQI;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getPM10() {
            return PM10;
        }

        public void setPM10(String PM10) {
            this.PM10 = PM10;
        }

        public String getCO() {
            return CO;
        }

        public void setCO(String CO) {
            this.CO = CO;
        }

        public String getNO2() {
            return NO2;
        }

        public void setNO2(String NO2) {
            this.NO2 = NO2;
        }

        public String getO3() {
            return O3;
        }

        public void setO3(String O3) {
            this.O3 = O3;
        }

        public String getSO2() {
            return SO2;
        }

        public void setSO2(String SO2) {
            this.SO2 = SO2;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
