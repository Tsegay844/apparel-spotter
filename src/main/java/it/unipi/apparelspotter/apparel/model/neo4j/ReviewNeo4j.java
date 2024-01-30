package it.unipi.apparelspotter.apparel.model.neo4j;

import org.springframework.data.neo4j.core.schema.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("Review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewNeo4j {

    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;

    @Property("_id")
    private String mongoId;

    @Property("Rating")
    private String rating;

    // Assuming that a Review can be written by only one Customer
    @Relationship(type = "WRITTEN_BY", direction = Relationship.Direction.OUTGOING)
    private CustomerNeo4j customer;

    // Assuming that a Review can be about only one Cloth
    @Relationship(type = "REVIEW_OF", direction = Relationship.Direction.OUTGOING)
    private ClothNeo4j cloth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public CustomerNeo4j getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerNeo4j customer) {
        this.customer = customer;
    }

    public ClothNeo4j getCloth() {
        return cloth;
    }

    public void setCloth(ClothNeo4j cloth) {
        this.cloth = cloth;
    }

}