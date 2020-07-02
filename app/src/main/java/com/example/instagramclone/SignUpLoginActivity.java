package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity
{
    private EditText edtuserLogin,edtuserSignUp,edtPasswordSignUp,edtPasswordLogin;
    private Button btnUserLogIn,btnUserSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);

        edtuserLogin=findViewById(R.id.edtUserLogin);
        edtuserSignUp=findViewById(R.id.edtUserSignUp);
        edtPasswordLogin=findViewById(R.id.edtPasswordSignIn);
        edtPasswordSignUp=findViewById(R.id.edtPasswordSignUp);
        btnUserLogIn=findViewById(R.id.btnSignIn);
        btnUserSignUp=findViewById(R.id.btnSignUp);

        btnUserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final ParseUser appUser=new ParseUser();
                appUser.setUsername(edtuserSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e)
                    {
                        if(e==null)
                        {
                            FancyToast.makeText(SignUpLoginActivity.this, appUser.get("username") + "is signed Up sucessfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                          else
                        {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                        }
                    }
                });

            }
        });


        btnUserLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtuserLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e)
                    {
                        if(user !=null && e==null)
                        {
                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + "is  sucessfully Logged In", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                        }
                    }
                });

            }
        });
    }
}