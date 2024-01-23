package it.unipi.apparelspotter.apparel.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewOf {
    @Id @GeneratedValue
    private Long id;

    @TargetNode
    private ClothNeo4j cloth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClothNeo4j getCloth() {
        return cloth;
    }

    public void setCloth(ClothNeo4j cloth) {
        this.cloth = cloth;
    }
}

