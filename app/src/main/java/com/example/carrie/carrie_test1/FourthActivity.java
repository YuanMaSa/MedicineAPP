package com.example.carrie.carrie_test1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
    }
    public void goback(View v){
        finish();
    }

}