package com.viperproject.main.router;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.viperproject.R;
import com.viperproject.common.BaseFragment;
import com.viperproject.main.MessagesActivity;
import com.viperproject.messagelist.MessageListFragmentBuilder;
import com.viperproject.preview.PreviewFragmentBuilder;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public class MessagesRouterImpl implements MessagesRouter {
    public static final int FRAGMENT_CONTAINER = R.id.fragment_container;
    private final MessagesActivity activity;

    public MessagesRouterImpl(MessagesActivity activity) {
        this.activity=activity;
    }

    @Override
    public void openMessage(String message) {
        setFragment(activity, new PreviewFragmentBuilder(message).build(),true);
    }

    @Override
    public void openMessagesList() {
        setFragment(activity, new MessageListFragmentBuilder().build(),false);
    }

    public void setFragment(FragmentActivity activity, BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(FRAGMENT_CONTAINER, fragment, fragment.getFragmentName());
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getFragmentName());
        }
        transaction.commitAllowingStateLoss();
    }
}
