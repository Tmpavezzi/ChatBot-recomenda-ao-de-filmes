package com.example.movie_recommendation_chatbot.model;

public class Movie {
    private String title;
    private  String year;
    private String genre;
    private String plot;


    public Movie(String title, String year, String genre, String plot) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.plot = plot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
    @Override
    public String toString(){
        return "Título: " + title + "\nAno: " + year + "\nGênero: " + genre + "\nSinopse: " + plot;
    }
}
