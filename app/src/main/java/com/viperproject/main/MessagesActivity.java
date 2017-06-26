package com.viperproject.main;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.viperproject.R;
import com.viperproject.TheApplication;
import com.viperproject.common.Layout;
import com.viperproject.common.MvprActivity;
import com.viperproject.main.di.MessagesScreenModule;
import com.viperproject.main.di.MessagesScreenSubComponent;
import com.viperproject.main.router.MessagesRouter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Layout(id = R.layout.activity_messages)
public class MessagesActivity extends MvprActivity<MessagesView, MessagesActivityPresenter, MessagesRouter> implements MessagesView {
    private ScopeHolder holder;
    private MessagesScreenModule module;
    private MessagesScreenSubComponent subComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().setRouter(getSubComponent().getMessagesRouter());
        getRouter().openMessagesList();
    }

    @NonNull
    @Override
    public MessagesActivityPresenter createPresenter() {
        return getSubComponent().getMessagesActivityPresenter();
    }

    public MessagesScreenSubComponent getSubComponent() {
        if (subComponent ==null){
            holder = (ScopeHolder) getLastCustomNonConfigurationInstance();
            if (holder == null) {
                module = new MessagesScreenModule();
                subComponent = ((TheApplication) getApplication()).getComponent()
                        .plus(module);
                holder = new ScopeHolder(module, subComponent);
            } else {
                module = holder.module;
                subComponent = holder.subcomponent;
            }

            module.setMessagesActivity(this);
        }
        return subComponent;
    }

    @AllArgsConstructor @Getter
    final static class ScopeHolder {
        MessagesScreenModule module;
        MessagesScreenSubComponent subcomponent;
    }

}
