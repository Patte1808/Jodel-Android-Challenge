package com.jodelapp.features.settings.presentation

import dagger.Module
import dagger.Provides

/**
 * Created by Pattelicious on 22.11.17.
 */

@Module
class SettingsModule(private val view: SettingsContract.View) {

    @Provides
    fun provideView(): SettingsContract.View = view

    @Provides
    fun providePresenter(presenter: SettingsContract.Presenter): SettingsContract.Presenter = presenter
}