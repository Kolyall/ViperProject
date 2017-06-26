package com.viperproject.main;

import com.viperproject.common.MvprPresenter;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public class MainPresenter extends MvprPresenter<MainView,MainRouter> {
    public void setFragmentClicked(String text) {
        getView().hideLayout();
        getRouter().setFragment("Text from editText: "+text);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
