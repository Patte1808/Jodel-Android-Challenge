package com.jodelapp.features.users.presentation;

import com.jodelapp.AppComponent;
import com.jodelapp.di.scope.ViewScope;
import com.jodelapp.features.users.usecases.UserProfileListUseCaseModule;

import dagger.Component;

/**
 * Created by Pattelicious on 22.10.17.
 */

@ViewScope
@Component(dependencies = AppComponent.class, modules = {UserProfileListModule.class, UserProfileListUseCaseModule.class})
public interface UserProfileListComponent {

    void inject(UserProfileListView view);

}