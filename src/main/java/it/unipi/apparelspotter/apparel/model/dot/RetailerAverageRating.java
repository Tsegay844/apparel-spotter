package it.unipi.apparelspotter.apparel.model.dot;

public class RetailerAverageRating {
    private String retailerId;  // This field corresponds to the retailer's ID
    private Double averageRating;  // This field holds the calculated average rating

    // Constructor
    public RetailerAverageRating(String retailerId, Double averageRating) {
        this.retailerId = retailerId;
        this.averageRating = averageRating;
    }

    // Getters and Setters
    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    // toString method for easy logging and debugging
    @Override
    public String toString() {
        return "RetailerAverageRating{" +
                "retailerId='" + retailerId + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }

    // hashCode and equals methods, if necessary
}