package com.viperproject.main.domain;


import com.viperproject.data.MessagesStorage;
import com.viperproject.viper.Interactor;

import java.util.Collection;

import rx.Observable;
import rx.Scheduler;

/**
 * ~ ~ ~ ~ Description ~ ~ ~ ~
 *
 * @author Dmytro Zaitsev
 * @since 2016-Jun-07, 10:32
 */
public final class GetMessagesInteractor extends Interactor<Integer, Collection<MessageViewModel>> {
  private final MessagesStorage storage;
  private final MessageMapper  mapper;

  public GetMessagesInteractor(Scheduler subscribeOn, Scheduler observeOn, MessagesStorage storage, MessageMapper mapper) {
    super(subscribeOn, observeOn);
    this.storage = storage;
    this.mapper = mapper;
  }

  @Override
  protected Observable<Collection<MessageViewModel>> createObservable(Integer amount) {
    return storage.getMessages(amount)
        .map(mapper::map);
  }
}
