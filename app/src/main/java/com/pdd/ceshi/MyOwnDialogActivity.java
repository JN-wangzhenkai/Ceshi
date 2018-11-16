package com.pdd.ceshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MyOwnDialogActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_own_dialog);

        button=findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Mydialog myDialog=new Mydialog(MyOwnDialogActivity.this);
               myDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT); //对话框大小应根据屏幕大小调整
//                myDialog.setContent("是否去激活");
//                myDialog.setOnPositiveListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(MyOwnDialogActivity.this,"YES", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                myDialog.setOnNegativeListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(MyOwnDialogActivity.this,"NO",Toast.LENGTH_SHORT).show();
//                        myDialog.dismiss();
//
//                    }
//                });
                myDialog.show();
            }
        });

    }
}
