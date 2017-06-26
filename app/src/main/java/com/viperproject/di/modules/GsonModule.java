package com.viperproject.di.modules;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

@Module
public class GsonModule {
    public static final String LOWER_CASE_WITH_UNDERSCORES = "LOWER_CASE_WITH_UNDERSCORES";
    //    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String GRAMO_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Provides
    @Singleton
    @Named(LOWER_CASE_WITH_UNDERSCORES)
    public Gson provideDefaultLowerCaseGson() {
        return new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat(GRAMO_DATE_FORMAT)
                .create();
    }
}
