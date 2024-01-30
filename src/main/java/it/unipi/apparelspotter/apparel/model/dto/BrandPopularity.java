package it.unipi.apparelspotter.apparel.model.dto;

public class BrandPopularity {
    private String brand;
    private Double averageRating;
    private Integer reviewCount;

    public BrandPopularity(String brand, Double averageRating, Integer reviewCount) {
        this.brand = brand;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
}



