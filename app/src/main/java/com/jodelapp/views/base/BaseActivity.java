package com.jodelapp.views.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by Pattelicious on 16.11.17.
 */

public class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback {

    private ProgressDialog progressDialog;
    private Unbinder unbinder;

    @Override
    public void showLoading() {
        hideLoading();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean isInternetAvailable() {
        return false;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
