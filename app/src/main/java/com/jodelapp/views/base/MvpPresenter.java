package com.jodelapp.views.base;

/**
 * Created by Pattelicious on 16.11.17.
 */

public interface MvpPresenter<T extends MvpView> {

    void onAttach(T view);
    void onDetach();
    void handleApiError(String errorMessage);
}
