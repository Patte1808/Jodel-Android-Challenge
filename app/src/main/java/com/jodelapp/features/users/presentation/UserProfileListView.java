package com.jodelapp.features.users.presentation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jodelapp.App;
import com.jodelapp.AppComponent;
import com.jodelapp.R;
import com.jodelapp.features.users.models.UserProfilePresentationModel;
import com.jodelapp.utilities.Preferences;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pattelicious on 22.10.17.
 */

public class UserProfileListView extends Fragment implements UserProfileListContract.View,
        View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    UserProfileListContract.Presenter presenter;

    @Inject
    Preferences sharedPreferences;

    @BindView(R.id.ls_username)
    TextView lsUserName;

    @BindView(R.id.ls_email)
    TextView lsUserEmail;

    @BindView(R.id.ls_user_profiles)
    RecyclerView lsUserProfiles;

    private UserProfileListComponent scopeGraph;
    private Unbinder unbinder;

    private List<UserProfilePresentationModel> users;

    UserProfilePresentationModel selectedUser;

    public static UserProfileListView getInstance() {
        return new UserProfileListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        setupScopeGraph(App.get(getActivity()).getAppComponent());
        unbinder = ButterKnife.bind(this, view);
        lsUserProfiles.setAdapter(new UserProfileListAdapter(Collections.emptyList(), this));
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

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void loadUserList(List<UserProfilePresentationModel> users) {
        this.users = users;
        UserProfilePresentationModel currentUser = sharedPreferences.getCurrentUser(users);
        UserProfileListAdapter adapter = new UserProfileListAdapter(users, this);
        lsUserName.setText(currentUser.getUsername());
        lsUserEmail.setText(currentUser.getEmail());
        lsUserProfiles.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initViews() {
        lsUserProfiles.setLayoutManager(new LinearLayoutManager(getActivity()));
        lsUserProfiles.setHasFixedSize(true);
    }

    private void setupScopeGraph(AppComponent appComponent) {
        scopeGraph = DaggerUserProfileListComponent.builder()
                .appComponent(appComponent)
                .userProfileListModule(new UserProfileListModule(this))
                .build();
        scopeGraph.inject(this);
    }

    @Override
    public void onClick(View view) {
        selectedUser = (UserProfilePresentationModel) view.getTag();
        /*
            Todo:
            Is using EventBus in this case good practice?
         */
        //EventBus.getDefault().postSticky(selectedUser);

        sharedPreferences.setCurrentUser(selectedUser.getId());

    }

    /*
        Todo:
        This is not ideal yet, because there could be other keys whose value can change.
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        UserProfilePresentationModel currentUser = this.sharedPreferences.getCurrentUser(this.users);

        lsUserName.setText(currentUser.getUsername());
        lsUserEmail.setText(currentUser.getEmail());
    }
}
