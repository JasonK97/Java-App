package com.example.myapplication.fragment;

public class  model {
    private String Comment;
    private Integer rating;

    public model() {
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return Comment ;
    }
}
