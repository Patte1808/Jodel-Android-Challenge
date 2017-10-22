package com.jodelapp.features.users.presentation;

import com.jodelapp.features.users.models.UserProfilePresentationModel;

import java.util.List;

/**
 * Created by Pattelicious on 22.10.17.
 */

public interface UserProfileListContract {

    interface View {

        void loadUserList(List<UserProfilePresentationModel> providers);
    }

    interface Presenter {

        void onAttached();

        void onDetached();
    }
}
