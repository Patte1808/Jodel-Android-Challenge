package com.jodelapp.features.albums.presentation;

import com.jodelapp.features.albums.usecases.GetAlbumListByUser;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.ThreadTransformer;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Pattelicious on 23.10.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class AlbumListPresenterTest {
    AlbumListContract.Presenter presenter;

    @Mock
    AlbumListContract.View view;

    @Mock
    ThreadTransformer threadTransformer;

    @Mock
    RxDisposableFactory rxDisposableFactory;

    @Mock
    GetAlbumListByUser getAlbumListByUser;

    @Before
    public void setUp() throws Exception {
        presenter = new AlbumListPresenter(view, getAlbumListByUser, threadTransformer, rxDisposableFactory);
    }
}
