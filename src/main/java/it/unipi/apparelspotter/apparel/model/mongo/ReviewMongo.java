package it.unipi.apparelspotter.apparel.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "reviews")

@Getter // Generates all the getters
@Setter // Generates all the setters
// Generates an all-args constructor
@EqualsAndHashCode // Generates equals and hashCode methods
@ToString // Generates toString method
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMongo {
        @Id
        private String id;

        private String reviewText;
        private int rating;
        private String customerId;
        private String clothId;
        private Date date;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getReviewText() {
                return reviewText;
        }

        public void setReviewText(String reviewText) {
                this.reviewText = reviewText;
        }

        public int getRating() {
                return rating;
        }

        public void setRating(int rating) {
                this.rating = rating;
        }

        public String getCustomerId() {
                return customerId;
        }

        public void setCustomerId(String customerId) {
                this.customerId = customerId;
        }

        public String getClothId() {
                return clothId;
        }

        public void setClothId(String clothId) {
                this.clothId = clothId;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }
}