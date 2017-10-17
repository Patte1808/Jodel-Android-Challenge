package com.jodelapp.views.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jodelapp.App;
import com.jodelapp.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.photos.presentation.UserPhotoListView;
import com.jodelapp.features.todos.presentation.UserTodoListView;

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

    @Override
    public void loadToDoPage() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.v_container, UserTodoListView.getInstance())
                .commit();
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

        selectFragmentForMenuItemId(item.getItemId());
        return true;
    }


    public void selectFragmentForMenuItemId(@IdRes int itemId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;

        switch(itemId) {
            case R.id.menu_user_profile:
                fragment = UserTodoListView.getInstance();
                break;

            case R.id.menu_photos:
                fragment = UserPhotoListView.getInstance();
                break;

            case R.id.menu_tasks:
                fragment = UserTodoListView.getInstance();
                break;

            default:
                fragment = UserTodoListView.getInstance();
                break;
        }

        fragmentManager.beginTransaction().replace(R.id.v_container, fragment).commit();
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
