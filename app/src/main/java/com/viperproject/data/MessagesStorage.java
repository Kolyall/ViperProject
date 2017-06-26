package com.viperproject.data;

import com.viperproject.data.objects.Message;

import java.util.List;

import rx.Observable;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public interface MessagesStorage {
    Observable<List<Message>> getMessages();

    Observable<List<Message>> getMessages(int count);
}
