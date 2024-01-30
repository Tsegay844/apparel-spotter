package it.unipi.apparelspotter.apparel.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.HashSet;
import java.util.Set;

@Node("Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerNeo4j {
    @Property("_id")
    @Id
    private String mongoId;
    @Property("location")
    private Point location;

    // Assuming you have other classes for Cloth and Retailer with a @Node annotation
    @Relationship(type = "LIKE", direction = Relationship.Direction.OUTGOING)
    private Set<ClothNeo4j> likedCloths = new HashSet<>();

    @Relationship(type = "FOLLOW", direction = Relationship.Direction.OUTGOING)
    private Set<RetailerNeo4j> followedRetailers = new HashSet<>();
    // Setters and Getters
    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Set<ClothNeo4j> getLikedCloths() {
        return likedCloths;
    }

    public void setLikedCloths(Set<ClothNeo4j> likedCloths) {
        this.likedCloths = likedCloths;
    }

    public Set<RetailerNeo4j> getFollowedRetailers() {
        return followedRetailers;
    }

    public void setFollowedRetailers(Set<RetailerNeo4j> followedRetailers) {
        this.followedRetailers = followedRetailers;
    }
}

