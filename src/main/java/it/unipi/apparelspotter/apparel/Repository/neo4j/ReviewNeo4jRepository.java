package it.unipi.apparelspotter.apparel.Repository.neo4j;
import it.unipi.apparelspotter.apparel.model.neo4j.ReviewNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface ReviewNeo4jRepository extends Neo4jRepository<ReviewNeo4j, String> {
    @Query("MATCH (c:Cloth {_id: $clothId}), (r:Review {_id: $reviewId}) " +
            "CREATE (r)-[:REVIEW_OF]->(c)")
    void createReviewOfRelationship(String reviewId, String clothId);

}
