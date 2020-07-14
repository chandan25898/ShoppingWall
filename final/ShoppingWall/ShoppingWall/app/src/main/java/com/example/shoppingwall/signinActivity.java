package com.example.shoppingwall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class signinActivity extends AppCompatActivity {

    EditText etname,etemail,etpassword,etmobile;

    //SQLiteDatabase db;
    RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        etname=(EditText)findViewById(R.id.signin_name_input);
        etemail=(EditText)findViewById(R.id.signin_email_input);
        etpassword=(EditText)findViewById(R.id.signin_password_input);
        etmobile=(EditText)findViewById(R.id.signin_phone_no_input);

        Button btnregister2=(Button)findViewById(R.id.signin_btn);

        btnregister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etname.getText().toString();
                final String emailid = etemail.getText().toString();
                final String password = etpassword.getText().toString();
                final String mobile = etmobile.getText().toString();

                rq = Volley.newRequestQueue(signinActivity.this);
                String url = "http://192.168.43.37/Lakshya/shoping_user_insert,php";


                StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("success")) {
                            Intent i = new Intent(signinActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(signinActivity.this, "Try again" + s, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(signinActivity.this, "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap hm = new HashMap();
                        hm.put("Name", name);
                        hm.put("Emailid", emailid);
                        hm.put("password", password);
                        hm.put("mobile", mobile);
                        return hm;
                    }
                };
                rq.add(sr);
            }


        });
    }
}



