package com.pdd.ceshi;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView mText;
    private EditText editText ,editTextCode;

    private Button button;
    private OkHttpClient client;
    private String mobile;
    private String code;
    private String mobile1;
   private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        mText = findViewById(R.id.text);
        editText = findViewById(R.id.edit_query);
        button = findViewById(R.id.button);
        editTextCode = findViewById(R.id.code);
        imageView=findViewById(R.id.image);

        mobile1 = editText.getText().toString();

        mText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String url="http://h.hiphotos.baidu.com/image/pic/item/c995d143ad4bd1137c1d50b556afa40f4afb0560.jpg";
                String url1="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517982926613&di=fec086b2596ec00d70d2e928421227a1&imgtype=0&src=http%3A%2F%2Fimages2015.cnblogs.com%2Fblog%2F855612%2F201701%2F855612-20170103091634066-1680757913.png";

                String url2="http://7xi8d6.com1.z0.glb.clouddn.com/16124047_121657248344062_4191645221970247680_n.jpg";
                RequestOptions options = new RequestOptions();
                options.centerCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .override(Target.SIZE_ORIGINAL)
                        .error(R.mipmap.ic_launcher)
                        .fallback(R.mipmap.ic_launcher);


                Glide.with(MainActivity.this).load(url1)
                        .listener(mRequestListener)
                        .apply(options)
                  .into(imageView);


            }
        });

    }

    RequestListener mRequestListener = new RequestListener() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            Log.d("1111111", "加载失败 ");
            Log.d("111111", "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
            imageView.setImageResource(R.mipmap.ic_launcher);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            Log.d("1111111", "加载成功 ");
            Log.e("11111111",  "model:"+model+" isFirstResource: "+isFirstResource);
            return false;
        }
    };
}
