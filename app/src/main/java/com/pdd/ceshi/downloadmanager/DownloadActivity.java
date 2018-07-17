package com.pdd.ceshi.downloadmanager;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pdd.ceshi.R;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class DownloadActivity extends Activity implements View.OnClickListener {
    private TextView down;
    private TextView progress;
    private TextView file_name;
    private ProgressBar pb_update;
    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    public static String downloadUrl = "http://ucdl.25pp.com/fs08/2017/01/20/2/2_87a290b5f041a8b512f0bc51595f839a.apk";
    Timer timer;
    long id;
    TimerTask task;
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int pro = bundle.getInt("pro");
            String name  = bundle.getString("name");
            pb_update.setProgress(pro);
            progress.setText(String.valueOf(pro)+"%");
            file_name.setText(name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        down = (TextView) findViewById(R.id.down);
        progress = (TextView) findViewById(R.id.progress);
        file_name = (TextView) findViewById(R.id.file_name);
        pb_update = (ProgressBar) findViewById(R.id.pb_update);
        down.setOnClickListener(this);
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        request = new DownloadManager.Request(Uri.parse(downloadUrl));

        request.setTitle("大象投教");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir() ;


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1000);
        }



        //设置文件存放路径
        request.setDestinationInExternalPublicDir( Environment.getExternalStorageDirectory().getPath()  , "app-release.apk" ) ;

        Log.d("111111111", "onCreate: "+Environment.getExternalStorageDirectory().getName());
        Log.d("111111111", "onCreate: "+Environment.getExternalStorageDirectory().getPath());
        Log.d("1111111111", "onCreate: "+Environment.getExternalStorageDirectory());
        Log.d("111111111", "onCreate: "+this.getFilesDir());

        pb_update.setMax(100);
        final  DownloadManager.Query query = new DownloadManager.Query();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                Cursor cursor = downloadManager.query(query.setFilterById(id));
                if (cursor != null && cursor.moveToFirst()) {
                    if (cursor.getInt(
                            cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        pb_update.setProgress(100);


                       // install(Environment.getExternalStorageDirectory().getPath() + "/storage/emulated/0/app-release.apk" );

                    installNormal(DownloadActivity.this,Environment.getExternalStorageDirectory().getPath()+"/storage/emulated/0");
                        task.cancel();
                    }
                    String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                    String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    int pro =  (bytes_downloaded * 100) / bytes_total;
                    Message msg =Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pro",pro);
                    bundle.putString("name",title);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                cursor.close();
            }
        };
        timer.schedule(task, 0,1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }


    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
            } else {
                // Permission Denied
            }
        }
    }


    @Override
    public void onClick(View v) {
        id = downloadManager.enqueue(request);
        task.run();
        down.setClickable(false);
//        down.setBackgroundResource(R.drawable.btn_disable_shape);

    }
    private void install(String path) {
        Log.d("11111", "install: "+path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//4.0以上系统弹出安装成功打开界面
        startActivity(intent);
    }


    //普通安装 兼容7.0
    private static void installNormal(Context context, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            File Path = new File(apkPath, "test");
            File newFile = new File(apkPath, "app-release.apk");
          //  File file = (new File(apkPath+"/storage/emulated/0/app-release.apk"));


            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, "com.pdd.ceshi.fileprovider", newFile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.parse("file://" + apkPath+"/app-release.apk"),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}