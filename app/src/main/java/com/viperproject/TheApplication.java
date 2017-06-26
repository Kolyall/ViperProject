package com.viperproject;

import android.support.multidex.MultiDexApplication;

import com.viperproject.di.component.AppComponent;
import com.viperproject.di.component.DaggerAppComponent;
import com.viperproject.di.modules.AppModule;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public class TheApplication extends MultiDexApplication {
    private AppComponent component;

    public AppComponent getComponent() {
        return component;
    }

    @Override public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
