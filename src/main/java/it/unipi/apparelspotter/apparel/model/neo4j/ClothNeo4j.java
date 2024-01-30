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
    @Property("_id")
    private String mongoId;
    @Relationship(type = "POSTED_BY", direction = Relationship.Direction.OUTGOING)
    private RetailerNeo4j postedByRetailer;

    public RetailerNeo4j getPostedByRetailer() {
        return postedByRetailer;
    }

    public void setPostedByRetailer(RetailerNeo4j postedByRetailer) {
        this.postedByRetailer = postedByRetailer;
    }

    public String getMongoId() {
        return mongoId;
    }


    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }



}