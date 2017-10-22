package com.jodelapp.features.users.presentation;

import android.util.Log;

import com.jodelapp.features.users.usecases.GetUserProfileList;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import javax.inject.Inject;

/**
 * Created by Pattelicious on 22.10.17.
 */

public class UserProfileListPresenter implements UserProfileListContract.Presenter {
    private final UserProfileListContract.View view;
    private final GetUserProfileList getUserProfileList;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public UserProfileListPresenter(UserProfileListContract.View view,
                                    GetUserProfileList getUserProfileList,
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory factory) {
        this.view = view;
        this.getUserProfileList = getUserProfileList;
        this.threadTransformer = threadTransformer;
        this.disposables = factory.get();
    }

    @Override
    public void onAttached() {
        disposables.add(getUserProfileList.call()
                .compose(threadTransformer.applySchedulers())
                .subscribe(
                        users -> view.loadUserList(users),
                        error -> Log.e("UserProfileList", error.getMessage())
                ));
    }

    @Override
    public void onDetached() {
        disposables.clear();
    }
}
