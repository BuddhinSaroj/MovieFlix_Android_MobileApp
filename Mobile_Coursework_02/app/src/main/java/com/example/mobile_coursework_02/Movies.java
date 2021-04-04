package com.example.mobile_coursework_02;

public class Movies {
    private int id;
    private String titleOfTheMovie;
    private String theYear;
    private String theDirector;
    private String listOfActors;
    private String ratings;
    private String review;
    private int Favourites;

    public Movies() {
    }

    public Movies(String titleOfTheMovie, String theYear, String theDirector, String listOfActors, String ratings, String review) {
        this.titleOfTheMovie = titleOfTheMovie;
        this.theYear = theYear;
        this.theDirector = theDirector;
        this.listOfActors = listOfActors;
        this.ratings = ratings;
        this.review = review;
    }

    public Movies(String titleOfTheMovie, String theYear, String theDirector, String listOfActors, String ratings, String review, int favourites) {
        this.titleOfTheMovie = titleOfTheMovie;
        this.theYear = theYear;
        this.theDirector = theDirector;
        this.listOfActors = listOfActors;
        this.ratings = ratings;
        this.review = review;
        this.Favourites = favourites;
    }

    public Movies(int id, String titleOfTheMovie, String theYear, String theDirector, String listOfActors, String ratings, String review, int favourites) {
        this.id = id;
        this.titleOfTheMovie = titleOfTheMovie;
        this.theYear = theYear;
        this.theDirector = theDirector;
        this.listOfActors = listOfActors;
        this.ratings = ratings;
        this.review = review;
        Favourites = favourites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getFavourites() {
        return Favourites;
    }

    public void setFavourites(int favourites) {
        Favourites = favourites;
    }

    @Override
    public String toString() {
        return "\n" +
                "Movie Title = " + titleOfTheMovie + '\n' +
                "Movie Director = " + theDirector + '\n' +
                "List of actors = " + listOfActors ;
    }
}
