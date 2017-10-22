package com.jodelapp.views.activities;

import android.support.test.runner.AndroidJUnit4;

import com.jodelapp.R;
import com.jodelapp.debug.test.BuildConfig;
import com.jodelapp.features.photos.presentation.UserPhotoListView;
import com.jodelapp.features.todos.presentation.UserTodoListView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;


/**
 * Created by Pattelicious on 22.10.17.
 */

public class AndroidMainActivityTest {

    @Mock
    MainActivityPresenter presenter;


    @Test
    public void shouldOpenToDoView_onToDoItemClicked(){
        presenter.selectFragmentForMenuItemId(R.id.menu_tasks);

        verify(view).goToView(isA(UserTodoListView.class));
    }

    @Test
    public void shouldOpenPhotoView_onPhotoItemClicked(){
        presenter.onNavigationItemSelected(R.id.menu_photos);

        verify(view).goToView(isA(UserPhotoListView.class));
    }
}
