package com.jodelapp.features.albums.presentation;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pattelicious on 17.10.17.
 */

@Module
public class AlbumListModule {
    private final AlbumListContract.View view;

    public AlbumListModule(AlbumListContract.View view) {
        this.view = view;
    }

    @Provides
    public AlbumListContract.View provideView() {
        return this.view;
    }

    @Provides
    public AlbumListContract.Presenter providePresenter(AlbumListPresenter presenter) {
        return presenter;
    }
}
