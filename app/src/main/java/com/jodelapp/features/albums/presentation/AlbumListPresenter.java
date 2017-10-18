package com.jodelapp.features.albums.presentation;

import android.util.Log;

import com.jodelapp.features.albums.models.AlbumPresentationModel;
import com.jodelapp.features.albums.usecases.GetAlbumListByUser;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import javax.inject.Inject;

/**
 * Created by Pattelicious on 17.10.17.
 */

public class AlbumListPresenter implements AlbumListContract.Presenter {

    private static final String USER_ID = "1";

    private final AlbumListContract.View view;
    private final GetAlbumListByUser getAlbumListByUser;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public AlbumListPresenter(AlbumListContract.View view,
                              GetAlbumListByUser getAlbumListByUser,
                              ThreadTransformer threadTransformer,
                              RxDisposableFactory factory) {
        // TODO: 8/13/17 your dependencies inject here
        this.view = view;
        this.getAlbumListByUser = getAlbumListByUser;
        this.threadTransformer = threadTransformer;
        this.disposables = factory.get();
    }


    @Override
    public void onAttached() {
        disposables.add(getAlbumListByUser.call(USER_ID)
                .compose(threadTransformer.applySchedulers())
                .subscribe(
                        albums -> view.loadAlbumList(albums),
                        error -> Log.e("AlbumList", error.getMessage())
                )
        );
    }

    @Override
    public void onDetached() {
        disposables.clear();
    }

    @Override
    public void showAlbumDetail(AlbumPresentationModel provider) {

    }
}
