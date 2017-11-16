package com.jodelapp.features.photos.presentation;

import android.util.Log;

import com.jodelapp.features.photos.usecases.GetPhotoListByAlbum;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import javax.inject.Inject;

public final class UserPhotoListPresenter implements UserPhotoListContract.Presenter {

    private final UserPhotoListContract.View view;
    private final GetPhotoListByAlbum getPhotoListByAlbum;
    private final ThreadTransformer threadTransformer;
    private final RxDisposables disposables;

    @Inject
    public UserPhotoListPresenter(UserPhotoListContract.View view,
                                  GetPhotoListByAlbum photoListByAlbum,
                                  ThreadTransformer threadTransformer,
                                  RxDisposableFactory factory) {
        // TODO: 8/13/17 your dependencies inject here
        this.view = view;
        this.getPhotoListByAlbum = photoListByAlbum;
        this.threadTransformer = threadTransformer;
        this.disposables = factory.get();
    }


    @Override
    public void onAttached() {
        disposables.add(getPhotoListByAlbum.call(view.getAlbumId())
                        .compose(threadTransformer.applySchedulers())
                        .subscribe(
                                photos -> view.loadPhotoList(photos),
                                error -> Log.e("PhotoList", error.getMessage())
                        )
        );
    }

    @Override
    public void onDetached() {
        disposables.clear();
    }


}
