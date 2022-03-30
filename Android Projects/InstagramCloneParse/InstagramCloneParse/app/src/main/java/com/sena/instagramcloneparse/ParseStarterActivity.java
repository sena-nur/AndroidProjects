package com.sena.instagramcloneparse;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Ch4sWT8wwQj7atBDohxKNfevct0ZlGCGHoCWfFhC")
                .clientKey("t08hYmzR0TjZ745gK1gutzGxMEgPz0CDNkleCJpe")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
