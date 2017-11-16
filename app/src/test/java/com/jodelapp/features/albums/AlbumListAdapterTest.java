package com.jodelapp.features.albums;

import android.support.v7.widget.RecyclerView;

import com.jodelapp.features.albums.models.AlbumPresentationModel;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pattelicious on 16.11.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class AlbumListAdapterTest {

    void shouldOpenPhotoView_onClickItem() {

        RecyclerView recyclerView = Mockito.mock(RecyclerView.class);
        List<AlbumPresentationModel> items = new ArrayList<>();
        AlbumPresentationModel albumPresentationModel = Mockito.mock(AlbumPresentationModel.class);
        items.add(albumPresentationModel);

        /*
            Todo:
            Can't unit test this, because I can't invoke the constructor
            How to handle that?
         */
        //AlbumListAdapter adapter = Mockito.when(AlbumListAdapter.class)
    }
}
