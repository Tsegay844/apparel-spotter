package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.neo4j.RetailerNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RetailerNeo4jRepository extends Neo4jRepository<RetailerNeo4j, String> {

    @Query("MATCH (retailer:Retailer) WHERE retailer._id = $mongoId RETURN retailer")
    Optional<RetailerNeo4j> findByMongoId(@Param("mongoId") String mongoId);
}
