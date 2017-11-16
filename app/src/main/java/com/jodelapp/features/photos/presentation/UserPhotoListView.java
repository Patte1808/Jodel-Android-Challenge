package com.jodelapp.features.photos.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jodelapp.App;
import com.jodelapp.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.photos.models.PhotoPresentationModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
    Should this be an activity rather than a fragment?
 */
public class UserPhotoListView extends Fragment implements UserPhotoListContract.View {


    @Inject
    UserPhotoListContract.Presenter presenter;

    @BindView(R.id.ls_user_photos)
    RecyclerView lsUserPhotos;

    private UserPhotoListComponent scopeGraph;
    private Unbinder unbinder;
    private String albumId;

    public String getAlbumId() {
        return albumId;
    }

    public static UserPhotoListView getInstance(String albumId) {
        UserPhotoListView userPhotoListView = new UserPhotoListView();
        Bundle bundle = new Bundle();
        bundle.putString("albumId", albumId);
        userPhotoListView.setArguments(bundle);

        return userPhotoListView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        setupScopeGraph(App.get(getActivity()).getAppComponent());
        unbinder = ButterKnife.bind(this, view);
        this.albumId = getArguments().getString("albumId");
        Log.wtf("UserPhotoListView", "onCreateView: " + this.albumId);
        initViews();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onAttached();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetached();
        unbinder.unbind();
    }

    private void setupScopeGraph(AppComponent appComponent) {
        scopeGraph = DaggerUserPhotoListComponent.builder()
                .appComponent(appComponent)
                .userPhotoListModule(new UserPhotoListModule(this))
                .build();
        scopeGraph.inject(this);
    }

    @Override
    public void loadPhotoList(List<PhotoPresentationModel> photos) {
        UserPhotoListAdapter userPhotoListAdapter = new UserPhotoListAdapter(photos);
        lsUserPhotos.setAdapter(userPhotoListAdapter);
        userPhotoListAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        lsUserPhotos.setLayoutManager(new LinearLayoutManager(getActivity()));
        lsUserPhotos.setHasFixedSize(true);
    }
}
