package com.jodelapp.features.todos.models;


public class TodoPresentationModel {

    private String id;
    private String title;
    private Boolean status;

    public TodoPresentationModel(String id, String title, Boolean status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getStatus() {
        return status;
    }
}
