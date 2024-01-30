package it.unipi.apparelspotter.apparel.model.dto;
import org.springframework.data.annotation.Id;
public class ClothIds {
    @Id
    private String id;
    private Double avgRating;
    private Integer reviewCount;

    public ClothIds(String id, Double avgRating, Integer reviewCount) {
        this.id = id;
        this.avgRating = avgRating;
        this.reviewCount = reviewCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

}