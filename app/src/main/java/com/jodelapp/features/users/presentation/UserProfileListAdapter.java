package com.jodelapp.features.users.presentation;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jodelapp.R;
import com.jodelapp.features.users.models.UserProfilePresentationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pattelicious on 22.10.17.
 */

public class UserProfileListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<UserProfilePresentationModel> profileDataList = new ArrayList<>();
    private final View.OnClickListener onClickListener;

    public UserProfileListAdapter(List<UserProfilePresentationModel> profileDataList,
                                  View.OnClickListener onClickListener) {
        this.profileDataList.clear();
        this.profileDataList.addAll(profileDataList);
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_user_profile, parent, false);
        v.setOnClickListener(onClickListener);
        return new UserProfileItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserProfilePresentationModel userProfilePresentationModel = profileDataList.get(position);
        ((UserProfileItemViewHolder) holder).render(userProfilePresentationModel);
    }


    @Override
    public int getItemCount() {
        return profileDataList.size();
    }


    class UserProfileItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected View parent;

        @BindView(R.id.tv_item_username)
        TextView tvItemUsername;

        UserProfileItemViewHolder(View view) {
            super(view);
            parent = ((ViewGroup) view).getChildAt(0);
            ButterKnife.bind(this, view);
        }

        void render(UserProfilePresentationModel userProfilePresentationModel) {
            tvItemUsername.setText(userProfilePresentationModel.getUsername());
        }

        @Override
        public void onClick(View view) {
            Log.wtf("Adasd", "onClick: Asdasd");
            onClickListener.onClick(parent);
        }
    }
}
