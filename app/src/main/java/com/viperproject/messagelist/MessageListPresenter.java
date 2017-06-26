package com.viperproject.messagelist;

import com.viperproject.common.MvprPresenter;
import com.viperproject.main.domain.GetMessagesInteractor;
import com.viperproject.main.domain.MessageViewModel;
import com.viperproject.main.router.MessagesRouter;

import java.util.Collection;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public class MessageListPresenter extends MvprPresenter<MessageListView,MessagesRouter> {
    private final GetMessagesInteractor interactor;
    private Collection<MessageViewModel> cachedData;

    public MessageListPresenter(GetMessagesInteractor interactor) {
        this.interactor= interactor;
    }

    public void fetchMessages(int count) {
        interactor.execute(
                // onNext
                messages -> {
                    cachedData = messages;
                    getView().onMessageListRecieved(messages);
                },
                // onError
                throwable -> {
                },
                // argument
                count);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (cachedData != null) {
            getView().onMessageListRecieved(cachedData);
        }else{
            fetchMessages(5);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        interactor.unsubscribe();
    }

    public void onMessageClicked(MessageViewModel item) {
        getRouter().openMessage(item.getMessage());
    }
}
