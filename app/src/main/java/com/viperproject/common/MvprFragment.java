package com.viperproject.common;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public abstract class MvprFragment<V extends MvpView, P extends MvprPresenter<V,R>, R extends MvprRouter> extends MvpFragment<V,P> {

    public R getRouter(){
        return getPresenter().getRouter();
    }

    @NonNull
    @Override
    public P getPresenter() {
        return super.getPresenter();
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

}
