package com.jodelapp.features.settings.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceActivity
import com.jodelapp.R

/**
 * Created by Pattelicious on 22.11.17.
 * Inspiration: https://developer.android.com/reference/android/preference/PreferenceActivity.html
 */

class SettingsView: PreferenceActivity(), SettingsContract.View {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        addPreferencesFromResource(R.xml.fragment_preferences)

    }

    override fun loadUserList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}