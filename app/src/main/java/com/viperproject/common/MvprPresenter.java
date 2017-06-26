package com.viperproject.common;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public abstract class MvprPresenter<V extends MvpView, R extends MvprRouter> extends MvpBasePresenter<V> {
    private R router;

    public R getRouter() {
        return router;
    }

    public void setRouter(R router) {
        this.router = router;
    }

    public abstract void onStart();

    public abstract void onStop();
}
