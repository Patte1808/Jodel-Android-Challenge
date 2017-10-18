package com.jodelapp.features.albums.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jodelapp.App;
import com.jodelapp.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.albums.models.AlbumPresentationModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pattelicious on 17.10.17.
 */

public class AlbumListView extends Fragment implements AlbumListContract.View {

    @Inject
    AlbumListContract.Presenter presenter;

    @BindView(R.id.ls_user_albums)
    RecyclerView lsUserAlbums;

    private AlbumListComponent scopeGraph;
    private Unbinder unbinder;

    public static AlbumListView getInstance() {
        return new AlbumListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        setupScopeGraph(App.get(getActivity()).getAppComponent());
        unbinder = ButterKnife.bind(this, view);
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
        scopeGraph = DaggerAlbumListComponent.builder()
                .appComponent(appComponent)
                .albumListModule(new AlbumListModule(this))
                .build();
        scopeGraph.inject(this);
    }

    @Override
    public void loadAlbumList(List<AlbumPresentationModel> albums) {
        AlbumListAdapter albumListAdapter = new AlbumListAdapter(albums);
        lsUserAlbums.setAdapter(albumListAdapter);
        albumListAdapter.notifyDataSetChanged();
    }

    @Override
    public void albumClick(AlbumPresentationModel provider) {
        presenter.showAlbumDetail(provider);
    }

    private void initViews() {
        lsUserAlbums.setLayoutManager(new LinearLayoutManager(getActivity()));
        lsUserAlbums.setHasFixedSize(true);
    }
}