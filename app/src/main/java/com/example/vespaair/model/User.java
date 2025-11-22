package com.example.vespaair.model;

public class User {
    private int id;
    private String name;
    private String email;
    private int userId;

    public User(String name, String email) { // <= Constructor wajib
        this.id = id;
        this.name = name;
        this.email = email;
        this.userId = userId;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getUserId() { return userId; }
}
