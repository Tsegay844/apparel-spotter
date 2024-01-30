package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.neo4j.ClothNeo4j;
import it.unipi.apparelspotter.apparel.model.neo4j.RetailerNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RetailerNeo4jRepository extends Neo4jRepository<RetailerNeo4j, String> {

    @Query("MATCH (retailer:Retailer) WHERE retailer._id = $mongoId RETURN retailer")
    Optional<RetailerNeo4j> findByMongoId(@Param("mongoId") String mongoId);

    @Query("MATCH (c:Customer {_id: $customerId}) " +
            "MATCH (r:Retailer) " +
            "WHERE r.location IS NOT NULL " +
            "WITH r, point.distance(c.location, r.location) AS dist " +
            "RETURN r._id AS retailerId " +
            "ORDER BY dist ASC " +
            "LIMIT 5")
    List<String> findNearbyRetailersByCustomerId(@Param("customerId") String customerId);
    @Query("MATCH (r:Retailer)<-[:FOLLOW]-() " +
            "WHERE r._id = $retailerId " +
            "RETURN COUNT(*) AS TotalLikes")
    Integer countTotalFollowersByRetailer(@Param("retailerId") String retailerId);

    @Query("MATCH (r:Retailer) WHERE r._id = $mongoId RETURN r")
    Optional<RetailerNeo4j> findRetailerByMongoId(String mongoId);
    @Query("MATCH (r:Retailer {_id: $id}) DETACH DELETE r")
    void deleteRetailerByPropertyId(@Param("id") String propertyId);



}
