package com.example.vespaair.model;

public class User {
    private int id;
    private String title;
    private String body;
    private int userId;

    public User(String name, String email) { // <= Constructor wajib
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public int getUserId() { return userId; }
}
