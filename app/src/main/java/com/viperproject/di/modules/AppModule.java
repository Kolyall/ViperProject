package com.viperproject.di.modules;

import android.app.Application;
import android.content.res.Resources;

import com.viperproject.common.di.Job;
import com.viperproject.common.di.Main;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

@Module
public final class AppModule {
    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Job
    static Scheduler provideJobScheduler() {
        return Schedulers.computation();
    }

    @Provides
    @Main
    static Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    Resources provideResources() {
        return app.getResources();
    }
}
