package it.unipi.apparelspotter.apparel.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
@Node("Retailer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailerNeo4j {

    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;


    // The externalId is assumed to be an identifier from an external database (like MongoDB)
    @Property("_id")
    private String mongoId;
    @Property("retailerName")
    private String retailerName;

    @Property("location")
    private Point location;

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

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}