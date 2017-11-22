package com.jodelapp.features.settings.presentation

/**
 * Created by Pattelicious on 22.11.17.
 */

interface SettingsContract {

    interface View {
        fun loadUserList()
    }

    interface Presenter {
        fun onAttached()

        fun onDetached()
    }
}