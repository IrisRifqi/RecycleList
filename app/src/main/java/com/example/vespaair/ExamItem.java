package com.example.vespaair;
public class ExamItem {
    private String name;
    private String date;
    private String message;
    private int image1;
    private int image2;

    public ExamItem (String name, String date, String message, int image1, int image2) {
        this.name = name;
        this.date = date;
        this.message = message;
        this.image1 = image1;
        this.image2 = image2;
    }


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public int getImage1() {
        return image1;
    }

    public int getImage2() {
        return image2;
    }
}


