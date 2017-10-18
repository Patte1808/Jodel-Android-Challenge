package com.jodelapp.features.albums.usecases;

import android.support.annotation.NonNull;

import com.jodelapp.data.api.ApiService;
import com.jodelapp.features.albums.models.AlbumPresentationModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Pattelicious on 17.10.17.
 */

public interface GetAlbumListByUser {
    Single<List<AlbumPresentationModel>> call(@NonNull String userId);
}

final class GetAlbumListImpl implements GetAlbumListByUser {

    private final ApiService apiService;

    @Inject
    public GetAlbumListImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<List<AlbumPresentationModel>> call(@NonNull String userId) {
        return apiService.getAlbums(userId)
                .flatMapIterable(albums -> albums)
                .map(album -> new AlbumPresentationModel(String.valueOf(album.getId()), String.valueOf(album.getUserId()), album.getTitle()))
                .toList();
    }
}

