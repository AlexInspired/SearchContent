package com.example.searchcontent;

import android.app.Application;
import com.example.searchcontent.dagger.*;

public class App extends Application {
    private static App sInstance;
    private AppComponent applicationComponent;

    public App() {
        sInstance = this;
    }

    public static AppComponent getInstance() {
        return sInstance.getAppComponent();
    }

    private AppComponent getAppComponent() {
        if (applicationComponent == null) {
            applicationComponent =  DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .netModule(new NetModule())
                    .roomModule(new RoomModule())
                    .build();
        }
        return applicationComponent;
    }

    @Override public void onCreate() {
        super.onCreate();
        getAppComponent();
    }
}
