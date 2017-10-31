package com.jodelapp.data;

/**
 * Created by Pattelicious on 31.10.17.
 */

public class Repository {
    private static Repository INSTANCE = null;

    private String currentUserId = null;

    private Repository() {
    }

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }

        return INSTANCE;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
}
