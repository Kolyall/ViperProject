package com.viperproject.messagelist;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.viperproject.main.domain.MessageViewModel;

import java.util.Collection;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public interface MessageListView extends MvpView {

    void onMessageListRecieved(Collection<MessageViewModel> messages);
}
