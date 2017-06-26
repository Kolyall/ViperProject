package com.viperproject.common;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.lang.annotation.Annotation;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.ButterKnife;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public abstract class BaseFragment<V extends MvpView, P extends MvprPresenter<V,R>, R extends MvprRouter> extends MvprFragment<V,P,R>{

    private static final AtomicInteger lastFragmentId = new AtomicInteger(0);
    private final int fragmentId;

    public BaseFragment() {
        fragmentId = lastFragmentId.incrementAndGet();
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return null;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        return inflater.inflate(layout.id(), null);
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    public String getFragmentName() {
        return Long.toString(fragmentId);
    }
}
