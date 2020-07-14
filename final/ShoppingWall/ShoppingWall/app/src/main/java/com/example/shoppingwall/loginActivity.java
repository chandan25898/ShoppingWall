package com.example.shoppingwall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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

public class loginActivity extends AppCompatActivity {

    EditText edemail,edpassword;
    TextView register;
    RequestQueue rq;
    SQLiteDatabase db;
    SharedPreferences sp;
    SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=openOrCreateDatabase("login",MODE_PRIVATE,null);
        sp=getSharedPreferences("login",MODE_PRIVATE);
        e=sp.edit();

        edemail=(EditText)findViewById(R.id.login_phone_no_input);
        edpassword=(EditText)findViewById(R.id.login_password_input);
        Button btnlogin=(Button)findViewById(R.id.login_btn);
        register=(TextView)findViewById(R.id.register);
        CheckBox checkBox=(CheckBox)findViewById(R.id.remember_me_checkbox);

        String text="register now";

        SpannableString ss=new SpannableString(text);
        ClickableSpan clickablespan1= new ClickableSpan() {
            @Override
            public void onClick(View widget)
            {
               Intent i=new Intent(loginActivity.this,signinActivity.class) ;
               startActivity(i);
            }
        };ss.setSpan(clickablespan1,0,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(ss);
        register.setMovementMethod(LinkMovementMethod.getInstance());

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String emailid = edemail.getText().toString();
                final String password1 = edpassword.getText().toString();

                rq= Volley.newRequestQueue(loginActivity.this);
                String url="http://192.168.43.37/Lakshya/shoping_user _check.php";
                StringRequest sr=new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s)
                    {
                        if(s.equals("valid"))
                        {
                            e.putString("emailid",emailid);
                            e.commit();
                            Intent i=new Intent(loginActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(loginActivity.this, "Try again"+s, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                        Toast.makeText(loginActivity.this, "Error-"+volleyError, Toast.LENGTH_LONG).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        HashMap hm=new HashMap();
                        hm.put("Emailid",emailid);
                        hm.put("password",password1);
                        return hm;
                    }
                };
                rq.add(sr);
               /* try
                {
                    Cursor cur = db.rawQuery("select * from users where emailid='" + emailid + "' and password='" + password1 + "'", null);
                    if (cur.getCount() > 0)
                    {
                        e.putString("emailid",emailid);
                        e.commit();
                        Intent i = new Intent(MainActivity.this,homepage.class);
                        startActivity(i);
                        finish();
                    } else
                        {
                        Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                        etemail.setText("");
                        etpassword.setText("");
                        etemail.requestFocus();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Error"+e, Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }
}
