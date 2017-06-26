package com.viperproject.messagelist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.viperproject.R;
import com.viperproject.common.BaseFragment;
import com.viperproject.common.Layout;
import com.viperproject.main.MessagesActivity;
import com.viperproject.main.di.MessagesScreenSubComponent;
import com.viperproject.main.domain.MessageViewModel;
import com.viperproject.main.router.MessagesRouter;

import java.util.Collection;

import butterknife.BindView;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */
@FragmentWithArgs
@Layout(id = R.layout.fragment_message_list)
public class MessageListFragment extends BaseFragment<MessageListView, MessageListPresenter, MessagesRouter> implements MessageListView, MessageView.OnMessageClickListener {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private MessageAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        getPresenter().setRouter(getSubComponent().getMessagesRouter());
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getAdapter().setOnMessageClickListener(this);
        mRecyclerView.setAdapter(getAdapter());
    }

    @Override
    public void onMessageListRecieved(Collection<MessageViewModel> messages) {
        getAdapter().addItems(messages);
    }

    @NonNull
    @Override
    public MessageListPresenter createPresenter() {
        return getSubComponent().getMessageListPresenter();
    }

    private MessagesScreenSubComponent getSubComponent() {
        return ((MessagesActivity)getActivity()).getSubComponent();
    }

    public MessageAdapter getAdapter() {
        if (mAdapter==null){
            mAdapter= new MessageAdapter();
        }
        return mAdapter;
    }

    @Override
    public void onMessageClicked(MessageViewModel item) {
        getPresenter().onMessageClicked(item);
    }
}
