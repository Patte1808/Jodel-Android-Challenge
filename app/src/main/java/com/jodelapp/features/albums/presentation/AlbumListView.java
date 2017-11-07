package com.jodelapp.features.albums.presentation;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jodelapp.App;
import com.jodelapp.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.albums.models.AlbumPresentationModel;
import com.jodelapp.features.photos.presentation.UserPhotoListView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pattelicious on 17.10.17.
 */

public class AlbumListView extends Fragment implements AlbumListContract.View, View.OnClickListener {

    @Inject
    AlbumListContract.Presenter presenter;

    @BindView(R.id.ls_user_albums)
    RecyclerView lsUserAlbums;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.backdrop)
    ImageView backdrop;

    private AlbumPresentationModel selectedAlbum;

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
        Picasso.with(view.getContext()).load("http://via.placeholder.com/350x150").into(backdrop);
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
        AlbumListAdapter albumListAdapter = new AlbumListAdapter(albums, this);
        lsUserAlbums.setAdapter(albumListAdapter);
        albumListAdapter.notifyDataSetChanged();
    }

    @Override
    public void albumClick(AlbumPresentationModel provider) {
        presenter.showAlbumDetail(provider);
    }

    private void initViews() {
        lsUserAlbums.setLayoutManager(new GridLayoutManager(getActivity(),2));
        lsUserAlbums.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        lsUserAlbums.setHasFixedSize(true);

        initCollapsingToolbar();
    }

    private void initCollapsingToolbar() {
        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        selectedAlbum = (AlbumPresentationModel) view.getTag();
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = UserPhotoListView.getInstance();

        fragmentManager.beginTransaction().replace(R.id.v_container, fragment).addToBackStack("photo_list").commit();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}