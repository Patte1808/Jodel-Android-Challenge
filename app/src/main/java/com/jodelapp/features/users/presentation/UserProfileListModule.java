package com.jodelapp.features.users.presentation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pattelicious on 22.10.17.
 */

@Module
public class UserProfileListModule {

    private final UserProfileListContract.View view;

    public UserProfileListModule(UserProfileListContract.View view) {
        this.view = view;
    }

    @Provides
    public UserProfileListContract.View provideView() {
        return this.view;
    }

    @Provides
    public UserProfileListContract.Presenter providePresenter(UserProfileListPresenter presenter) {
        return presenter;
    }
}
