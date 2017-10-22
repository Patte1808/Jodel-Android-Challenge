package com.jodelapp.features.users.usecases;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pattelicious on 22.10.17.
 */

@Module
public class UserProfileListUseCaseModule {

    @Provides
    public GetUserProfileList provideGetAllAvailableProviders(GetUserProfileListImpl usecase) {
        return usecase;
    }
}
