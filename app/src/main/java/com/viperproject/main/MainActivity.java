package com.viperproject.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;

import com.viperproject.R;
import com.viperproject.common.Layout;
import com.viperproject.common.MvprActivity;
import com.viperproject.common.MvprFragment;
import com.viperproject.preview.PreviewFragmentBuilder;

import butterknife.BindView;
import butterknife.OnClick;

@Layout(id = R.layout.activity_main)
public class MainActivity extends MvprActivity<MainView, MainPresenter, MainRouter> implements MainView, MainRouter {
    public static final int FRAGMENT_CONTAINER = R.id.fragment_container;

    @BindView(R.id.editText) EditText mEditText;
    @BindView(R.id.mainLayout) View mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().setRouter(this);
    }

    @OnClick({R.id.submitButton})
    public void submitButtonOnClick(View view) {
        getPresenter().setFragmentClicked(mEditText.getText().toString());
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setFragment(String title) {
        setFragment(this, new PreviewFragmentBuilder(title).build(),false);
    }

    public void setFragment(FragmentActivity activity, MvprFragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(FRAGMENT_CONTAINER, fragment, fragment.getFragmentName());
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getFragmentName());
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void hideLayout() {
        mMainLayout.setVisibility(View.GONE);
    }
}
