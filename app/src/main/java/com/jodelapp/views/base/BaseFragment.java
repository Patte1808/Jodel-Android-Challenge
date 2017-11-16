package com.jodelapp.views.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jodelapp.utilities.UtilsComponent;

import butterknife.Unbinder;

/**
 * Created by Pattelicious on 16.11.17.
 */

public class BaseFragment extends Fragment implements MvpView {

    private BaseActivity activity;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.activity = activity;
            activity.onFragmentAttached();
        }
    }

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
        if(activity != null) {
            activity.onError(message);
        }
    }

    @Override
    public void showMessage(String message) {
        if(activity != null) {
            activity.showMessage(message);
        }
    }

    @Override
    public boolean isInternetAvailable() {
        if (activity != null) {
            return activity.isNetworkConnected();
        }

        return false;
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
