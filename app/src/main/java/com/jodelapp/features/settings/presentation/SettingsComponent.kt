package com.jodelapp.features.settings.presentation

import com.jodelapp.AppComponent
import com.jodelapp.di.scope.ViewScope
import dagger.Component

/**
 * Created by Pattelicious on 22.11.17.
 */

@ViewScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(SettingsModule::class))
interface SettingsComponent {
    fun inject(settingsView: SettingsView)
}