package com.example.instagramclone;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity implements  View.OnClickListener {

    private EditText punch_Power,kick_Speed,kick_Power,punch_Speed,name;
    Button btncreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         kick_Speed=findViewById(R.id.kick_Speed);
         kick_Power=findViewById(R.id.kick_Power);
         punch_Speed=findViewById(R.id.punch_Speed);
         punch_Power=findViewById(R.id.punch_Power);
         name=findViewById(R.id.name);
        btncreate=findViewById(R.id.btnCteate);

        btncreate.setOnClickListener(SignUp.this);

    }


        public void onClick(View v)
        {

            try {
                final ParseObject kickBoxer = new ParseObject("KickBoxer");
                kickBoxer.put("name", name.getText().toString());
                kickBoxer.put("punch_speed", Integer.parseInt(punch_Speed.getText().toString()));
                kickBoxer.put("punchPower", Integer.parseInt(punch_Power.getText().toString()));
                kickBoxer.put("kickSpeed", Integer.parseInt(kick_Speed.getText().toString()));
                kickBoxer.put("kickPower", Integer.parseInt(kick_Power.getText().toString()));
                kickBoxer.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)

                            FancyToast.makeText(SignUp.this, kickBoxer.get("name") + "is saved sucessfully to the server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        else
                            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }

                });
            }
            catch(Exception e)
            {
                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true);
            }

        }
    }