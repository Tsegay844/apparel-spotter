package it.unipi.apparelspotter.apparel.model.neo4j;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.geo.Point;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerNeo4j {

    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;


    // The externalId is assumed to be an identifier from an external database (like MongoDB)
    @Property("_id")
    private String mongoId;

    @Property("location")
    private Point location;

}