package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.neo4j.ClothNeo4j;
import it.unipi.apparelspotter.apparel.model.neo4j.TopLikedClothOfRetailer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothNeo4jRepository extends Neo4jRepository<ClothNeo4j, String> {
    @Query("MATCH (r:Retailer)<-[:POSTED_BY]-(c:Cloth)<-[:LIKE]-(customer:Customer) " +
            "WHERE r._id = $retailerId " +
            "RETURN c._id AS clothId, COUNT(customer) AS likes " +
            "ORDER BY likes DESC " +
            "LIMIT 5")
    List<TopLikedClothOfRetailer> findTop5ClothsByLikesForRetailer(@Param("retailerId") String retailerId);
    @Query("MATCH (r:Retailer)<-[:POSTED_BY]-(c:Cloth)<-[:LIKE]-() " +
            "WHERE r._id = $retailerId " +
            "RETURN COUNT(*) AS TotalLikes")
    Integer countTotalLikesByRetailer(@Param("retailerId") String retailerId);
    @Query("MATCH (customer:Customer)-[:FOLLOW]->(r:Retailer) " +
            "WHERE r._id = $retailerId " +
            "RETURN COUNT(customer) AS TotalFollowers")
    Integer countTotalFollowersByRetailer(@Param("retailerId") String retailerId);
}
