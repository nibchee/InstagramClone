package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class UsersPost extends AppCompatActivity
{
    private  LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);
        linearLayout=findViewById(R.id.linearLayout);

        Intent receivedIntentObject=getIntent();
        final String  recievedUserName=receivedIntentObject.getStringExtra("username");

        setTitle(recievedUserName+"'s posts");

        ParseQuery<ParseObject> parseQuery=new ParseQuery<>("Photo");
                parseQuery.whereEqualTo("username",recievedUserName);
                parseQuery.orderByDescending("createdAt");  //to get posts in descending order

        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                if( objects.size()>0 && e==null)
                {
                    for(ParseObject post:objects)
                    {

                        TextView postDescription=new TextView(UsersPost.this);

                        LinearLayout.LayoutParams des_params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        des_params.setMargins(5,5,5,15);
                        postDescription.setLayoutParams(des_params);
                        postDescription.setGravity(Gravity.CENTER);
                        postDescription.setBackgroundColor(Color.YELLOW);
                        postDescription.setTextColor(Color.RED);
                        postDescription.setTextSize(30f);
                        postDescription.setText(post.get("img_des")+"");
                        linearLayout.addView(postDescription);

                        //for getting picture
                        ParseFile postPicture=(ParseFile)post.get("picture");
                        postPicture.getDataInBackground(new GetDataCallback()
                        {
                            @Override
                            public void done(byte[] data, ParseException e)
                            {
                                if(data!=null && e==null)
                                {
                                    Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                    ImageView postImageView=new ImageView(UsersPost.this);

                                    LinearLayout.LayoutParams imageView_params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                    imageView_params.setMargins(5,5,5,5);
                                    postImageView.setLayoutParams(imageView_params);
                                    postImageView.setLayoutParams(imageView_params);
                                    postImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    postImageView.setImageBitmap(bitmap);

                                    linearLayout.addView(postImageView);
                                }

                            }
                        });
                    }
                }
                else//no objects
                    {
                        FancyToast.makeText(UsersPost.this,recievedUserName+" doesn't have any post", Toast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        finish();
                }
                dialog.dismiss();

            }
        });


    }


}