package com.jodelapp.features.albums.usecases;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pattelicious on 17.10.17.
 */

@Module
public class AlbumListUseCaseModule {

    @Provides
    public GetAlbumListByUser provideGetAllAvailableProviders(GetAlbumListImpl usecase) {
        return usecase;
    }
}