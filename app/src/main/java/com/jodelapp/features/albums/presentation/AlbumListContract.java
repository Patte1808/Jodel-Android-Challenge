package com.jodelapp.features.albums.presentation;

import com.jodelapp.features.albums.models.AlbumPresentationModel;

import java.util.List;

/**
 * Created by Pattelicious on 17.10.17.
 */

public interface AlbumListContract {

    interface View {

        void loadAlbumList(List<AlbumPresentationModel> providers);
    }

    interface Presenter {
        void onAttached();

        void onDetached();
    }
}