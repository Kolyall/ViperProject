package com.viperproject.data.mock;

import android.content.res.Resources;

import com.google.gson.reflect.TypeToken;
import com.viperproject.R;
import com.viperproject.common.data.mock.MockService;
import com.viperproject.data.MessagesStorage;
import com.viperproject.data.objects.DataResponse;
import com.viperproject.data.objects.Message;

import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

import static java.util.concurrent.TimeUnit.SECONDS;
import static rx.Observable.error;
import static rx.Observable.just;

public final class MockMessagesStorage extends MockService implements MessagesStorage {
    private final SecureRandom random = new SecureRandom();

    public MockMessagesStorage(Resources resources) {
        super(resources);
    }

    public Observable<DataResponse<List<Message>>> getDataResponse() {
        Type type = new TypeToken<DataResponse<List<Message>>>() {}.getType();
        return getResponseFromRes(R.raw.mock_get_messages, type);
    }

    public Observable<List<Message>> getMessages() {
        return getDataResponse().map(new Func1<DataResponse<List<Message>>, List<Message>>() {
            @Override
            public List<Message> call(DataResponse<List<Message>> response) {
                return response.getData();
            }
        });
    }

    /**
     * Emulates long operation
     */
    public Observable<List<Message>> getMessages(int count) {
        final Observable<Long> timer = Observable.timer(random.nextInt(2) + 1, SECONDS);
        return count < 0 ?
                timer.concatMap(seconds -> error(new RuntimeException("Count cannot be less than zero. Given count = " + count)))
                : getMessages().flatMap(new Func1<List<Message>, Observable<List<Message>>>() {
            @Override
            public Observable<List<Message>> call(List<Message> messages) {
                return just(randomSublist(messages, count, random)).zipWith(timer, (cheeses, seconds) -> cheeses);
            }
        });
    }
}
