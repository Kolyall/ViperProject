/*
 * Copyright 2016 Dmytro Zaitsev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.viperproject.main.di;


import com.viperproject.common.di.Job;
import com.viperproject.common.di.Main;
import com.viperproject.data.DataModule;
import com.viperproject.data.MessagesStorage;
import com.viperproject.main.MessagesActivity;
import com.viperproject.main.MessagesActivityPresenter;
import com.viperproject.main.domain.GetMessagesInteractor;
import com.viperproject.main.domain.MessageMapper;
import com.viperproject.main.router.MessagesRouter;
import com.viperproject.main.router.MessagesRouterImpl;
import com.viperproject.messagelist.MessageListPresenter;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module(includes = DataModule.class)
public final class MessagesScreenModule {
  private MessagesActivity activity;

  public void setMessagesActivity(MessagesActivity activity) {
    this.activity = activity;
  }

  @Provides
  MessagesRouter provideMessagesRouter() {
    return new MessagesRouterImpl(activity);
  }

  @Provides
  @MessagesScreenScope
  static GetMessagesInteractor provideGetMessagesInteractor(@Job Scheduler jobScheduler, @Main Scheduler mainScheduler,
                                                          MessagesStorage storage, MessageMapper mapper) {
    return new GetMessagesInteractor(jobScheduler, mainScheduler, storage, mapper);
  }

  @Provides
  @MessagesScreenScope
  static MessageMapper provideMessageMapper() {
    return new MessageMapper();
  }

  @Provides
  @MessagesScreenScope
  static MessageListPresenter provideMessageListPresenter(GetMessagesInteractor interactor) {
    return new MessageListPresenter(interactor);
  }

  @Provides
  @MessagesScreenScope
  static MessagesActivityPresenter provideMessagesActivityPresenter() {
    return new MessagesActivityPresenter();
  }
}
