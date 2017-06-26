package com.viperproject.common.data.mock;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viperproject.di.modules.GsonModule;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import static java.util.Collections.shuffle;


public class MockService {
    Resources resources;
    public MockService(Resources resources) {
        this.resources=resources;
    }

//    public Observable<UserInfo> getCurrentUserInfoObservable() {
//        return getResponseFromRes(R.raw.mock_current_user,UserInfo.class);
//    }

    // ------private methods------

//    @NonNull
//    protected <T> Observable<T> getResponseFromRes(@RawRes int rawId, final Class<T> cls) {
//        return getJsonFromRes(rawId)
//                .map(new Func1<JSONObject, T>() {
//                    @Override
//                    public T call(JSONObject jsonObject) {
//                        return jsonToObj(jsonObject, cls);
//                    }
//                });
//    }

    @NonNull
    protected <T> Observable<T> getResponseFromRes(@RawRes int rawId, final Type type) {
        return getJsonFromRes(rawId)
                .map(new Func1<JSONObject, T>() {
                    @Override
                    public T call(JSONObject jsonObject) {
                        return jsonToObj(jsonObject, type);
                    }
                });
    }

    @NonNull
    private Observable<JSONObject> getJsonFromRes(@RawRes int rawId) {
        return readStringFromResObservable(rawId)
                .map(new Func1<String, JSONObject>() {
                    @Override
                    public JSONObject call(String jsonString) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(jsonString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return jsonObject;
                    }
                })
                .filter(new Func1<JSONObject, Boolean>() {
                    @Override
                    public Boolean call(JSONObject jsonObject) {
                        return jsonObject!=null;
                    }
                });
    }

    @NonNull
    private Observable<String> readStringFromResObservable(@RawRes final int rawId) {
        return Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;

                try {
                    String jsonString = readJsonFromRes();
                    subscriber.onNext(jsonString);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }

            private String readJsonFromRes() throws IOException {
                InputStream is = resources.openRawResource(rawId);
                Writer writer = new StringWriter();
                char[] buffer = new char[1024];
                try {
                    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    int n;
                    while ((n = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, n);
                    }
                } finally {
                    is.close();
                }

                return writer.toString();
            }
        });
    }

//    private <T> T jsonToObj(JSONObject jsonObject, Class<T> cls) {
//        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .setDateFormat(GsonModule.GRAMO_DATE_FORMAT).create();
//        return gson.fromJson(jsonObject.toString(), cls);
//    }

    private <T> T jsonToObj(JSONObject jsonObject,  final Type type) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat(GsonModule.GRAMO_DATE_FORMAT).create();
        return gson.fromJson(jsonObject.toString(), type);
    }

    protected static <T> List<T> randomSublist(List<T> list, int count, Random random) {
        final ArrayList<T> shuffled = new ArrayList<>(list);
        shuffle(shuffled, random);
        return shuffled.subList(0, Math.min(count, shuffled.size()));
    }
}
