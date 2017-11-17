package com.jodelapp.utilities

import android.content.SharedPreferences
import com.jodelapp.Consts
import com.jodelapp.features.users.models.UserProfilePresentationModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Pattelicious on 17.11.17.
 */

@Singleton class Preferences @Inject constructor(val sharedPreferences: SharedPreferences) {
    fun setCurrentUser(currentUserId: String) {
        sharedPreferences.edit().putString(Consts.SHARED_PREF_CURRENT_USER, currentUserId).commit();
    }

    fun getCurrentUser(): String {
        return sharedPreferences.getString(Consts.SHARED_PREF_CURRENT_USER, "1");
    }

    fun getCurrentUser(userList: List<UserProfilePresentationModel>): UserProfilePresentationModel {
        return userList.filter { user -> user.id == getCurrentUser() }.first();
    }
}