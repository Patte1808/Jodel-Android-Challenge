package com.jodelapp.features.albums.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jodelapp.R;
import com.jodelapp.features.albums.models.AlbumPresentationModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pattelicious on 17.10.17.
 */

public class AlbumListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<AlbumPresentationModel> albumDataList = new ArrayList<>();

    private final View.OnClickListener onClickListener;

    public AlbumListAdapter(List<AlbumPresentationModel> albumDataList, View.OnClickListener onClickListener) {
        this.albumDataList.clear();
        this.albumDataList.addAll(albumDataList);
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_album, parent, false);
        v.setOnClickListener(onClickListener);
        return new AlbumItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumPresentationModel albumPresentationModel = albumDataList.get(position);
        ((AlbumItemViewHolder) holder).render(albumPresentationModel);
    }


    @Override
    public int getItemCount() {
        return albumDataList.size();
    }


    class AlbumItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected View parent;

        @BindView(R.id.tv_item_album_title)
        TextView tvItemTitle;

        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        AlbumItemViewHolder(View view) {
            super(view);
            parent = ((ViewGroup) view).getChildAt(0);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view)
        {
            onClickListener.onClick(parent);
        }

        void render(AlbumPresentationModel albumPresentationModel) {
            /*Picasso.with(this.itemView.getContext()).load(albumPresentationModel.getUrl())
                    .into(tvItemPhoto);*/
            tvItemTitle.setText(albumPresentationModel.getTitle());
            Picasso.with(this.itemView.getContext()).load(R.mipmap.jodellogo)
                    .into(thumbnail);
        }
    }
}
