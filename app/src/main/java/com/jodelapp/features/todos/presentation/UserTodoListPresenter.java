package com.jodelapp.features.todos.presentation;


import android.util.Log;

import com.jodelapp.features.todos.usecases.GetTodoListByUser;
import com.jodelapp.utilities.Preferences;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import javax.inject.Inject;

public final class UserTodoListPresenter implements UserTodoListContract.Presenter {

    //private static final String USER_ID = "1";
    private String userId;

    private final UserTodoListContract.View view;
    private final GetTodoListByUser getTodoListByUser;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;
    private final Preferences preferences;

    @Inject
    public UserTodoListPresenter(UserTodoListContract.View view,
                                 GetTodoListByUser getTodoListByUser,
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory factory,
                                 Preferences preferences) {
        this.view = view;
        this.getTodoListByUser = getTodoListByUser;
        this.threadTransformer = threadTransformer;
        this.disposables = factory.get();
        this.preferences = preferences;
    }

    @Override
    public void onAttached() {
        this.userId = preferences.getCurrentUser();
        disposables.add(getTodoListByUser.call(this.userId)
                .compose(threadTransformer.applySchedulers())
                .subscribe(
                        todos -> view.loadToDoList(todos),
                        error -> Log.e("UserToDo", error.getMessage())
                ));
    }

    @Override
    public void onDetached() {
        disposables.clear();
    }
}
