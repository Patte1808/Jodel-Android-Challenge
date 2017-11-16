package com.jodelapp.features.photos.presentation;


import com.jodelapp.features.photos.models.PhotoPresentationModel;

import java.util.List;

public interface UserPhotoListContract {

    interface View {

        void loadPhotoList(List<PhotoPresentationModel> providers);
        String getAlbumId();
    }

    interface Presenter {
        void onAttached();

        void onDetached();
    }
}
