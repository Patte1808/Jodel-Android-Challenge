package com.jodelapp.views.base;

/**
 * Created by Pattelicious on 16.11.17.
 */

public interface MvpView {
    void showLoading();

    void hideLoading();

    void onError(String message);

    void showMessage(String message);

    boolean isInternetAvailable();
}
