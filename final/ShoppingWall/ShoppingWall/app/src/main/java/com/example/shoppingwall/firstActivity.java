package com.example.shoppingwall;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class firstActivity extends AppCompatActivity {

    private static int timeout=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i=new Intent(firstActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },timeout);
    }
}
