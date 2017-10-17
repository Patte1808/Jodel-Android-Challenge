package com.jodelapp.features.photos.presentation;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jodelapp.R;
import com.jodelapp.features.photos.models.PhotoPresentationModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserPhotoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<PhotoPresentationModel> photoDataList = new ArrayList<>();

    public UserPhotoListAdapter(List<PhotoPresentationModel> photoDataList) {
        this.photoDataList.clear();
        this.photoDataList.addAll(photoDataList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PhotoPresentationModel photoPresentationModel = photoDataList.get(position);
        ((PhotoItemViewHolder) holder).render(photoPresentationModel);
    }


    @Override
    public int getItemCount() {
        return photoDataList.size();
    }


    class PhotoItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_photo)
        ImageView tvItemPhoto;

        @BindView(R.id.tv_item_title)
        TextView tvItemTitle;

        @BindView(R.id.tv_item_photo_card_view)
        CardView tvItemPhotoCardView;

        PhotoItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void render(PhotoPresentationModel photoPresentationModel) {
            Picasso.with(this.itemView.getContext()).load(photoPresentationModel.getUrl())
                    .into(tvItemPhoto);
            tvItemTitle.setText(photoPresentationModel.getTitle());
            tvItemPhotoCardView.setCardBackgroundColor(ContextCompat.getColor(this.itemView.getContext(), R.color.red));

            /* TODO: Change colors dynamically
            int currentColorPosition = getAdapterPosition() % Color.values().length;
            Color color;

            switch(currentColorPosition) {
                case 0:
                    color = Color.BLUE;
                    break;
                case 1:
                    color = Color.RED;
                    break;
                case 3:
                    color = Color.YELLOW;
                    break;
                case 4:
                    color = Color.GREEN;
                    break;
                case 5:
                    color = Color.TURQUOISE;
                    break;
                case 6:
                    color = Color.ORANGE;
                    break;
                default:
                    color = Color.BLUE;
                    break;
            }
            */
        }
    }
}
