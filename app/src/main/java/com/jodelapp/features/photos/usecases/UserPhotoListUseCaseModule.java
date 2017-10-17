package com.jodelapp.features.photos.usecases;


import dagger.Module;
import dagger.Provides;

@Module
public class UserPhotoListUseCaseModule {

    @Provides
    public GetPhotoListByAlbum provideGetAllAvailableProviders(GetPhotoListByAlbumImpl usecase) {
        return usecase;
    }
}
