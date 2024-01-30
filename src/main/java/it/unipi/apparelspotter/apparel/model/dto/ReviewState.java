package it.unipi.apparelspotter.apparel.model.dto;

public class ReviewState {

    private int numberOfReviews;
    private double averageRating;

    public ReviewState(int numberOfReviews, double averageRating) {
        this.numberOfReviews = numberOfReviews;
        this.averageRating = averageRating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

}