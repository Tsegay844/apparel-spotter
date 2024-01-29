package it.unipi.apparelspotter.apparel.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.util.List;

@Node("Retailer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailerNeo4j {
    // The externalId is assumed to be an identifier from an external database (like MongoDB)
    @Id
    @Property("_id")
    private String mongoId;
    @Property("location")
    private Point location;
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
}