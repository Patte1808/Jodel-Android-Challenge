package com.jodelapp.views.activities;

import com.jodelapp.R;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import javax.inject.Inject;

public final class MainActivityPresenter implements MainActivityContract.Presenter {

    private final MainActivityContract.View view;
    private final RxDisposables disposables;

    @Inject
    public MainActivityPresenter(MainActivityContract.View view,
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory rxDisposableFactory) {
        this.view = view;
        this.disposables = rxDisposableFactory.get();
    }


    @Override
    public void onCreate() {
        view.loadToDoPage();
    }

    @Override
    public void onDestroy() {
        disposables.clear();
    }

    @Override
    public void onNavigationItemSelected(int itemId) {
        switch(itemId) {
            case R.id.menu_user_profile:
                view.loadUserProfilePage();
                break;

            case R.id.menu_photos:
                view.loadUserAlbumPage();
                break;

            case R.id.menu_tasks:
                view.loadToDoPage();
                break;

            default:
                view.loadToDoPage();
                break;
        }
    }


}
