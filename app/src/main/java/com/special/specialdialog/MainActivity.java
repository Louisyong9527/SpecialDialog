package com.special.specialdialog;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.special.wheelview.SpcialDialog;
import com.special.wheelview.util.DateUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvClick;
    private SpcialDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tvClick = findViewById(R.id.tv_click);

        tvClick.setOnClickListener(this);



    }

    private void showDialog(){

        mDialog = new SpcialDialog(this);

        mDialog.setTitle("").setItems(createArrays()).setButtonText("确定").setDialogStyle(Color
                .parseColor("#895D13")).setCount(3).show();



        mDialog.setOnDialogItemClickListener(new SpcialDialog.OnDialogItemClickListener() {
            @Override
            public void onItemClick(int position, Object s) {
                Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private ArrayList<String> createArrays() {

        ArrayList<String> list = new ArrayList<String>();

        int yy = DateUtils.getYY();
        int mm = DateUtils.getMM();

        for (int i=1;i<=12;i++){
            list.add((yy-1)+"年"+i+"月");
        }

        for (int i=1;i<=mm;i++){
            list.add(yy+"年"+i+"月");
        }

        return list;
    }

    @Override
    public void onClick(View view) {
        showDialog();
    }
}
