package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("wWL3IA0vDt3o8Ftgb2r4EPNvwft0t97l4ssXX8IR")
                // if defined
                .clientKey("jUeMpO0IlacoHNWNLkyBeRPnZW2TwRFOLRe9z7AI")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
