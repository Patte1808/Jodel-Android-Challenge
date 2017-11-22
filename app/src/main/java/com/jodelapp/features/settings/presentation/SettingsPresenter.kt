package com.jodelapp.features.settings.presentation

import com.jodelapp.utilities.rx.RxDisposableFactory
import com.jodelapp.utilities.rx.RxDisposables
import com.jodelapp.utilities.rx.ThreadTransformer
import javax.inject.Inject

/**
 * Created by Pattelicious on 22.11.17.
 */

class SettingsPresenter: SettingsContract.Presenter {

    private val view: SettingsContract.View
    private val transformer: ThreadTransformer
    private val disposables: RxDisposables

    @Inject
    public constructor(view: SettingsContract.View,
                       transformer: ThreadTransformer,
                       disposables: RxDisposableFactory) {

        this.view = view
        this.transformer = transformer
        this.disposables = disposables.get()

    }

    override fun onAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDetached() {
        this.disposables.clear()
    }
}