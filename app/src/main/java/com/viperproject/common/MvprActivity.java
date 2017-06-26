package com.viperproject.common;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public abstract class MvprActivity<V extends MvpView, P extends MvprPresenter<V,R>, R extends MvprRouter> extends MvpActivity<V,P> {
    public R getRouter(){
        return getPresenter().getRouter();
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.id());
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
    }
}
