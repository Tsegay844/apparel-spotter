package it.unipi.apparelspotter.apparel.Service;

import it.unipi.apparelspotter.apparel.Repository.mongo.ClothMongoRepository;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RelationshipService {

    @Autowired
    private ClothMongoRepository clothMongoRepository; // Repository to access MongoDB

    @Autowired
    private Neo4jTemplate neo4jTemplate; // Neo4jTemplate to execute Cypher queries

    public void createReviewOfRelationshipsInBatch(List<String> clothMongoIds) {
        // Prepare a list to hold pairs of clothId (B) and reviewId (A) from MongoDB
        List<Map<String, String>> pairs = new ArrayList<>();

        // Retrieve each cloth by its MongoDB id and extract the review _id (A) and main cloth _id (B)
        for (String clothMongoId : clothMongoIds) {
            ClothMongo clothMongo = clothMongoRepository.findById(clothMongoId)
                    .orElseThrow(() -> new RuntimeException("Cloth not found in MongoDB"));
            String reviewIdEmbeddedInCloth = clothMongo.getReviewId(); // This is A
            String mainClothId = clothMongo.getMainClothId(); // This is B
            pairs.add(Map.of("clothId", mainClothId, "reviewId", reviewIdEmbeddedInCloth));
        }

        // Construct the Cypher query
        String query =
                "UNWIND $pairs AS pair " +
                        "MATCH (c:Cloth {_id: pair.clothId}), (r:Review {_id: pair.reviewId}) " +
                        "WHERE pair.reviewId = r._id " + // This ensures A = C
                        "MERGE (r)-[rel:REVIEW_OF]->(c) " +
                        "RETURN count(rel)"; // This returns the number of created relationships

        // Execute the Cypher query with the pairs as parameters
        Map<String, Object> params = Collections.singletonMap("pairs", pairs);
        Integer createdCount = neo4jTemplate.query(query, params).fetchAs(Integer.class).one().orElse(0);

        System.out.println("Created " + createdCount + " relationships.");
    }
}