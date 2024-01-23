package it.unipi.apparelspotter.apparel.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Node("Cloth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothNeo4j {

    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;


    // The externalId is assumed to be an identifier from an external database (like MongoDB)
    @Property("_id")
    private String mongoId;

    @Property("Brand")
    private String bran;
    @Relationship(type = "REVIEW_OF", direction = Relationship.Direction.INCOMING)
    private List<ReviewOf> reviews;

    public String getBran() {
        return bran;
    }

    public void setBran(String bran) {
        this.bran = bran;
    }

    public List<ReviewOf> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewOf> reviews) {
        this.reviews = reviews;
    }

    // Constructor, getters, and setters


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




}