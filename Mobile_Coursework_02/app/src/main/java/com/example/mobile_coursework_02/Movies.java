package com.example.mobile_coursework_02;

public class Movies {
    private String titleOfTheMovie;
    private String theYear;
    private String theDirector;
    private String listOfActors;
    private String ratings;
    private String review;

    public Movies(String titleOfTheMovie, String theYear, String theDirector, String listOfActors, String ratings, String review) {
        this.titleOfTheMovie = titleOfTheMovie;
        this.theYear = theYear;
        this.theDirector = theDirector;
        this.listOfActors = listOfActors;
        this.ratings = ratings;
        this.review = review;
    }

    public String getTitleOfTheMovie() {
        return titleOfTheMovie;
    }

    public void setTitleOfTheMovie(String titleOfTheMovie) {
        this.titleOfTheMovie = titleOfTheMovie;
    }

    public String getTheYear() {
        return theYear;
    }

    public void setTheYear(String theYear) {
        this.theYear = theYear;
    }

    public String getTheDirector() {
        return theDirector;
    }

    public void setTheDirector(String theDirector) {
        this.theDirector = theDirector;
    }

    public String getListOfActors() {
        return listOfActors;
    }

    public void setListOfActors(String listOfActors) {
        this.listOfActors = listOfActors;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
