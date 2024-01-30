package it.unipi.apparelspotter.apparel.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "reviews")

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMongo {
        @Id
        private String id;
        private String Title;
        private String reviewText;
        private int rating;
        private Date date;

        public String getTitle() {
                return Title;
        }

        public void setTitle(String title) {
                Title = title;
        }

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


        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }
}