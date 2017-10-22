package com.jodelapp.views.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jodelapp.App;
import com.jodelapp.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.albums.presentation.AlbumListView;
import com.jodelapp.features.todos.presentation.UserTodoListView;
import com.jodelapp.features.users.presentation.UserProfileListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainActivityContract.Presenter presenter;

    @BindView(R.id.tb_app)
    Toolbar tbApp;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private MainActivityComponent scopeGraph;

    private void inflateFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.v_container, fragment)
                .commit();
    }

    @Override
    public void loadToDoPage() {
        inflateFragment(UserTodoListView.getInstance());
    }

    @Override
    public void loadUserAlbumPage() {
        inflateFragment(AlbumListView.getInstance());
    }

    @Override
    public void loadUserProfilePage() {
        inflateFragment(UserProfileListView.getInstance());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupScopeGraph(App.get(this).getAppComponent());
        initViews();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        presenter.onCreate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        presenter.onNavigationItemSelected(item.getItemId());
        return true;
    }

    private void setupScopeGraph(AppComponent appComponent) {
        scopeGraph = DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        scopeGraph.inject(this);
    }

    private void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(tbApp);
    }

}
