package com.example.multichatclient.controller;

public class Message {
    private String name;
    private String text;
    private boolean is_current_user;

    public Message (String name, String text, boolean user) {
        this.name = name;
        this.text = text;
        is_current_user = user;
    }

    public String getText() {
        return text;
    }

    public String  getName() {
        return name;
    }

    public boolean isBelongsToCurrentUser() {
        return is_current_user;
    }
}
