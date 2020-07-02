package com.example.instagramclone;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity implements  View.OnClickListener {

    private EditText punch_Power,kick_Speed,kick_Power,punch_Speed,name;
    Button btncreate;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allKickBoxers;

    private Button btnTransition;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         kick_Speed=findViewById(R.id.kick_Speed);
         kick_Power=findViewById(R.id.kick_Power);
         punch_Speed=findViewById(R.id.punch_Speed);
         punch_Power=findViewById(R.id.punch_Power);
         name=findViewById(R.id.name);

         btnTransition=findViewById(R.id.btnTransition);
        btncreate=findViewById(R.id.btnCteate);
       txtGetData=findViewById(R.id.txtGetData);
         btncreate.setOnClickListener(SignUp.this);
        btnGetAllData=findViewById(R.id.btnGetAllData);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("kickBoxer");
                parseQuery.getInBackground("ZXNV1sqnVM", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e)
                    {
                        if(object!=null && e==null)
                        {
                            txtGetData.setText(object.get("name")+"");
                        }



                    }
                });
            }
        });


        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers="";

               ParseQuery<ParseObject> queryAll=ParseQuery.getQuery("KickBoxer");
               queryAll.whereGreaterThan("punchPower",100);
               queryAll.setLimit(1);
               queryAll.findInBackground(new FindCallback<ParseObject>() {
                   @Override
                   public void done(List<ParseObject> objects, ParseException e) {
                       if(e==null)
                       {
                           if(objects.size()>0)
                           {
                               for(ParseObject kickBoxer:objects)
                               {
                                   allKickBoxers+=kickBoxer.get("name")+"\n";
                               }
                               FancyToast.makeText(SignUp.this,   allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                           }
                           else
                           {
                               FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                           }
                       }
                   }
               });
            }
        });


        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent(SignUp.this,SignUpLoginActivity.class);
             startActivity(intent);
            }
        });
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