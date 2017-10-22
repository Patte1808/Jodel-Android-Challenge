package com.jodelapp.views.activities;

import com.jodelapp.R;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.ThreadTransformer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


/**
 * Created by Pattelicious on 22.10.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class AndroidMainActivityTest {

    MainActivityContract.Presenter presenter;

    @Mock
    MainActivityContract.View view;

    @Mock
    ThreadTransformer threadTransformer;

    @Mock
    RxDisposableFactory rxDisposableFactory;

    @Before
    public void setUp() throws Exception {
        presenter = new MainActivityPresenter(view, threadTransformer, rxDisposableFactory);
    }

    @Test
    public void shouldOpenToDoView_onToDoItemClicked(){
        presenter.onNavigationItemSelected(R.id.menu_tasks);

        verify(view).loadToDoPage();
    }

    @Test
    public void shouldOpenPhotoView_onPhotoItemClicked(){
        presenter.onNavigationItemSelected(R.id.menu_photos);

        verify(view).loadUserAlbumPage();
    }

    @Test
    public void shouldOpenProfileView_onProfileItemClicked(){
        presenter.onNavigationItemSelected(R.id.menu_user_profile);

        verify(view).loadUserProfilePage();
    }
}
