package com.jodelapp.data.events;

import com.jodelapp.features.users.models.UserProfilePresentationModel;

/**
 * Created by Pattelicious on 08.11.17.
 */

public class UserEvent {
    private UserProfilePresentationModel userProfile;

    public UserEvent(UserProfilePresentationModel userProfile) {
        this.userProfile = userProfile;
    }

    public UserProfilePresentationModel getUserProfile() {
        return this.userProfile;
    }
}
