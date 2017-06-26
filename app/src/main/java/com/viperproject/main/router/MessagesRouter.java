package com.viperproject.main.router;

import com.viperproject.common.MvprRouter;

/**
 * Created by Nick Unuchek on 26.06.2017.
 */

public interface MessagesRouter extends MvprRouter {
    void openMessage(String title);

    void openMessagesList();
}
