package it.unipi.apparelspotter.apparel.model.dto;

public class RetailerAverageRating {
    private String retailerId;
    private Double averageRating;


    public RetailerAverageRating(String retailerId, Double averageRating) {
        this.retailerId = retailerId;
        this.averageRating = averageRating;
    }


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


    @Override
    public String toString() {
        return "RetailerAverageRating{" +
                "retailerId='" + retailerId + '\'' +
                ", averageRating=" + averageRating +
                '}';
    }


}