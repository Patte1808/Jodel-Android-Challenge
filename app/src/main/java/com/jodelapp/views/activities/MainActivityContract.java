package com.jodelapp.views.activities;


public interface MainActivityContract {

    interface View {


        void loadToDoPage();
        void loadUserAlbumPage();
        void loadUserProfilePage();
    }

    interface Presenter {

        void onCreate();

        void onDestroy();

        void onNavigationItemSelected(int itemId);
    }
}
