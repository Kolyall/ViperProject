package com.viperproject.preview;

import com.viperproject.common.MvprPresenter;
import com.viperproject.main.router.MessagesRouter;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public class PreviewPresenter extends MvprPresenter<PreviewView,MessagesRouter> {
    public void setArgTitle(String title) {
        getView().setTitle(title);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
