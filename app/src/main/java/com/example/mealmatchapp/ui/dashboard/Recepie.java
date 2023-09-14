package com.example.mealmatchapp.ui.dashboard;

public class Recepie {
    public String title;
    public String difficulty;
    public String duration;
    public int imageId;

    public Recepie(String title, String difficulty, String duration, int imageId) {
        this.title = title;
        this.difficulty = difficulty;
        this.duration = duration;
        this.imageId = imageId;
    }
}
