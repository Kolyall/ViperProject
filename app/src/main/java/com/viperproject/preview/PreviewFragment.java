package com.viperproject.preview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.viperproject.R;
import com.viperproject.common.Layout;
import com.viperproject.common.MvprFragment;
import com.viperproject.main.MainRouter;

import butterknife.BindView;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */
@FragmentWithArgs
@Layout(id = R.layout.fragment_preview)
public class PreviewFragment extends MvprFragment<PreviewView, PreviewPresenter, MainRouter> implements PreviewView {

    @Arg String title;

    @BindView(R.id.title) TextView mTitleView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().setArgTitle(title);
    }

    @Override
    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    @NonNull
    @Override
    public PreviewPresenter createPresenter() {
        return new PreviewPresenter();
    }
}
