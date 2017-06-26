package com.viperproject.common;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.lang.annotation.Annotation;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.ButterKnife;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public abstract class MvprFragment<V extends MvpView, P extends MvprPresenter<V,R>, R extends MvprRouter> extends MvpFragment<V,P> {

    private static final AtomicInteger lastFragmentId = new AtomicInteger(0);
    private final int fragmentId;

    public MvprFragment() {
        fragmentId = lastFragmentId.incrementAndGet();
    }

    public R getRouter(){
        return getPresenter().getRouter();
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MvprActivity activity = (MvprActivity) getActivity();

        //Передаем презентеру роутер
        ((MvprPresenter) getPresenter()).setRouter(activity.getRouter());
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Очищаем роутер у презентера
        getPresenter().setRouter(null);
    }

    public String getFragmentName() {
        return Long.toString(fragmentId);
    }
}
