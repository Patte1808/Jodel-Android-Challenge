package com.jodelapp.features.photos.usecases;

import android.support.annotation.NonNull;

import com.jodelapp.data.api.ApiService;
import com.jodelapp.features.photos.models.PhotoPresentationModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Pattelicious on 17.10.17.
 */

public interface GetPhotoListByAlbum {
    Single<List<PhotoPresentationModel>> call(@NonNull String albumId);
}

final class GetPhotoListByAlbumImpl implements GetPhotoListByAlbum {

    private final ApiService apiService;

    @Inject
    public GetPhotoListByAlbumImpl(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Single<List<PhotoPresentationModel>> call(@NonNull String albumId) {
        return apiService.getPhotos(albumId)
                .flatMapIterable(photos -> photos)
                .map(photo -> new PhotoPresentationModel(String.valueOf(photo.getId()), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl()))
                .toList();
    }
}
