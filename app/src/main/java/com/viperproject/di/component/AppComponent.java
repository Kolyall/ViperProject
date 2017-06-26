package com.viperproject.di.component;

import com.viperproject.di.modules.AppModule;
import com.viperproject.main.di.MessagesScreenModule;
import com.viperproject.main.di.MessagesScreenSubComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    MessagesScreenSubComponent plus(MessagesScreenModule module);
}

