package com.pdd.ceshi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Imageput extends AppCompatActivity {

    private Uri uri;
    private Call<UploadResult> uploadCall;
    private Call<UpdateResult> updataCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageput);

        uri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + getResources().getDrawable(R.mipmap.ic_launcher));

        ContextCompat.getDrawable(getApplicationContext(),R.mipmap.ic_launcher);

       // 读取到流中，转换成bitmap，再从bitmap转变成file



        @SuppressLint("ResourceType")
        InputStream is = getResources().openRawResource(R.drawable.sel_no);


        Bitmap bitmap = BitmapFactory.decodeStream(is);

        String defaultPath = getApplicationContext().getFilesDir()
                .getAbsolutePath() + "/defaultGoodInfo";
        File file = new File(defaultPath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            return;
        }
        String defaultImgPath = defaultPath + "/messageImg.jpg";
        file = new File(defaultImgPath);
        try {
            file.createNewFile();

            FileOutputStream fOut = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG, 20, fOut);
            is.close();
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        uploadPhoto(file);
    }

    /**
     * 上传头像
     */
    public void uploadPhoto(File file) {

        File ff = new File("E:\\MyAndroidProjects\\Ceshi\\app\\src\\main\\java\\com\\pdd\\ceshi\\raw\\2112.png");

        Log.d("111111111", "uploadPhoto: "+ff);

        // 请求体
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        // 多部分-部分请求
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", "photo.png", requestBody);

        if (uploadCall != null) uploadCall.cancel();
//        uploadCall = NetClient.getInstance().getUserApi().upload(part);
        uploadCall.enqueue(uploadCallback);
    }

    /**
     * 上传头像Callback
     */
    private Callback<UploadResult> uploadCallback = new Callback<UploadResult>() {
        @Override
        public void onResponse(Call<UploadResult> call, Response<UploadResult> response) {


            Log.d("11111111", "onResponse: "+response.body().toString());

            if (response != null && response.isSuccessful()) {
                UploadResult result = response.body();
                if (result == null) {

                    return;
                }
                if (result.getCount() != 1) {

                    return;
                }
                String photoUrl = result.getUrl();
                // 截取出后面图像文件名称(在更新头像时，服务器接口只要求传过去文件的名称)
                String phoneName = result.getUrl();
                phoneName = phoneName.substring(phoneName.lastIndexOf("/") + 1, phoneName.length());
                // 成功上传后 --- 通知服务器更新头像


            }
        }

        @Override
        public void onFailure(Call<UploadResult> call, Throwable t) {
            Log.d("11111111", "onFailure: "+call +t);
        }
    };
}
