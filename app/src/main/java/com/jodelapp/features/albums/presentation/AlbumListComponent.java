package com.jodelapp.features.albums.presentation;

import com.jodelapp.AppComponent;
import com.jodelapp.di.scope.ViewScope;
import com.jodelapp.features.albums.usecases.AlbumListUseCaseModule;

import dagger.Component;

/**
 * Created by Pattelicious on 17.10.17.
 */

@ViewScope
@Component(dependencies = AppComponent.class, modules = {AlbumListModule.class, AlbumListUseCaseModule.class})
public interface AlbumListComponent {

    void inject(AlbumListView view);

}
