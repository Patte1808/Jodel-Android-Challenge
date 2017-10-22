package com.jodelapp.features.users.usecases;

import com.jodelapp.data.api.ApiService;
import com.jodelapp.features.users.models.UserProfilePresentationModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Pattelicious on 22.10.17.
 */

public interface GetUserProfileList {

    Single<List<UserProfilePresentationModel>> call();
}

final class GetUserProfileListImpl implements GetUserProfileList {

    private final ApiService apiService;

    @Inject
    public GetUserProfileListImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<UserProfilePresentationModel>> call() {
        return apiService.getUsers()
                .flatMapIterable(users -> users)
                .map(user -> new UserProfilePresentationModel(String.valueOf(user.getId()), user.getUsername(), user.getEmail()))
                .toList();
    }
}