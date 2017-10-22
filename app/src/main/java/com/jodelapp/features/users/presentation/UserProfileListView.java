package com.jodelapp.features.users.presentation;

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

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pattelicious on 22.10.17.
 */

public class UserProfileListView extends Fragment implements UserProfileListContract.View {

    @Inject
    UserProfileListContract.Presenter presenter;

    @BindView(R.id.ls_username)
    TextView lsUserName;

    @BindView(R.id.ls_user_profiles)
    RecyclerView lsUserProfiles;

    private UserProfileListComponent scopeGraph;
    private Unbinder unbinder;

    public static UserProfileListView getInstance() {
        return new UserProfileListView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
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

    @Override
    public void loadUserList(List<UserProfilePresentationModel> users) {
        UserProfileListAdapter adapter = new UserProfileListAdapter(users);
        lsUserProfiles.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lsUserName.setText(users.get(1).getUsername());
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
}
