package com.pdd.ceshi.downloadmanager;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pdd.ceshi.NetClient;
import com.pdd.ceshi.R;
import com.pdd.ceshi.UpdateResult;
import com.pdd.ceshi.UploadResult;
import com.pdd.ceshi.Utils.AES;
import com.pdd.ceshi.Utils.AesEcbUtils;
import com.pdd.ceshi.Utils.AesUtils;
import com.pdd.ceshi.Utils.MD5Utils;
import com.pdd.ceshi.Utils.OKHttpRetrofitConfig;
import com.pdd.ceshi.raw.ImageApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload extends AppCompatActivity implements View.OnClickListener {


    private Uri uri;
    private Call<ResponseBody> uploadCall;
    private Call<ResponseBody> updataCall;

    private TextView textView1, textView2;
    String mString = "chat message secret test string";
    byte[] mBytes = null;


    private String url = "http://192.168.1.117:8080/oxygen-community-webServer/community/";
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        file = new File("/storage/emulated/0/DCIM/100ANDRO/ic_launcher.png");

        findViewById(R.id.split).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String s="dsafas|sdafa||123|435";
//                Log.d("111111111", "onClick: "+ Arrays.asList(s.split("\\|"+"#"+"\\|")));

//                String ss=String.valueOf(StringTofloat("CA8ADB41"));
//                Log.d("1111111", "onClick: "+ss);



                uploadPhoto(file);
            }
        });



        // 请求体
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);

        Log.d("11111111", "onCreate: " + file.length());
        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  uploadPhoto(file);

                String token=file.length()+" bytes"+"pddwangluoyonghutoken2018";

                uploadImageWithMonbile( MD5Utils.calc(token),file,requestBody);
            }
        });


        findViewById(R.id.encrpt).setOnClickListener(this);

        findViewById(R.id.dencrpt).setOnClickListener(this);


    }

    public float StringTofloat(String in) {
        byte[] ss = hexStringToBytes(in);
        float d = getFloat(ss);
        return d;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();//转换成大写字符串
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();//字符串转换成字符数组
        byte[] d = new byte[length];//4个
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            Log.d("1111111", "hexStringToBytes: di"+d[i]);
        }
        try {
            Log.d("1111111", "hexStringToBytes:----- "+new String(d,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("1111111", "hexStringToBytes: 000"+((byte)(12<<4)|(10)));
        return d;

    }
    public static float getFloat(byte[] b) {
        // 4 bytes
        int accum = 0;
        for (int shiftBy = 0; shiftBy < 4; shiftBy++) {
            accum |= (b[shiftBy] & 0xff) << shiftBy * 8;
        }
        return Float.intBitsToFloat(accum);//返回对应于给定位表示形式的 float 值
    }

    private static byte charToByte(char c) {
        Log.d("1111111", "charToByte: "+(byte)"0123456789ABCDEF".indexOf(c));
        Log.d("1111111", "charToByte: "+"0123456789ABCDEF".indexOf(c));

        return (byte) "0123456789ABCDEF".indexOf(c);//报告指定 Unicode 字符在此字符串中的第一个匹配项的从零开始的索引。
    }

    /**
     * 上传头像
     */
    public void uploadPhoto(File file) {


        // 请求体
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        // 多部分-部分请求
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", "photo.png", requestBody);

        if (uploadCall != null) uploadCall.cancel();
        uploadCall = NetClient.getInstance().getUserApi().upload(part);
        uploadCall.enqueue(uploadCallback);
    }

    /**
     * 上传头像Callback
     */
    private Callback<ResponseBody> uploadCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


            try {
                Log.d("11111111", "onResponse: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.d("11111111", "onFailure: " + call + t);
        }
    };



    private void uploadImageWithMonbile(String token, File file, RequestBody requestBody) {
        Log.d("00000000", "uploadImageWithMonbile -----------");
        String baseUrl = url;

        Map<String, RequestBody> params = new HashMap<>();
        params.put("token", toRequestBody(token));
        params.put("file\";filename=\"" + file.getName(), requestBody);

        OKHttpRetrofitConfig config = new OKHttpRetrofitConfig();

        ImageApi api = config.setCofing(baseUrl, ImageApi.class);

        Call<ResponseBody> uploadCall = api.upLoadImage(params);

        uploadCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("00000000", "onResponse: " + response.body());


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.d("000000000", "onFailure: " + call.toString() + "----" + t.toString());
            }
        });
    }

    public static RequestBody toRequestBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    String s="";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.encrpt:
//                try {
//                  s=AesUtils.encrypt("Pdd&chat*Msg1808#key","hello");
//                    Log.d("000000000", "onClick: "+s);
//                    } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;

                cbc();

        //        ecb();

            case R.id.dencrpt:
                try {

                    Log.d("000000000", "onCreate: "+AesUtils.decrypt("Pdd&chat*Msg1808#key",s));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }



    private String key="Pdd&amp;chat*Msg";

    private void ecb() {
        byte[] encrypt = new byte[0];
        try {
            encrypt = AesEcbUtils.encrypt("hello".getBytes("UTF8"), key.getBytes("UTF8"));

            Log.d("000000000", "加密: "+new String(encrypt));
            byte[] decrypt = AesEcbUtils.decrypt(encrypt, key.getBytes("UTF8"));
            Log.d("000000000", "ecb: "+new String(decrypt));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    //cbc算法加密
    private void cbc() {
        AES mAes = new AES();
        try {
            mBytes = mString.getBytes("UTF8");
        } catch (Exception e) {
            Log.d("00000000", "MainActivity----catch");
        }
        String enString = mAes.encrypt(mBytes);
        Log.d("0000000000", "onClick: "+"加密后：" + enString);

        String deString = mAes.decrypt(enString);
        Log.d("0000000000", "onClick: "+"解密后：" + deString);
    }
}
