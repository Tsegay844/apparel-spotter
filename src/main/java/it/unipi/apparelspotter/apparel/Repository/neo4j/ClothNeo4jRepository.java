package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.dto.ClothFollowLike;
import it.unipi.apparelspotter.apparel.model.dto.TopLikedCloth;
import it.unipi.apparelspotter.apparel.model.neo4j.ClothNeo4j;
import it.unipi.apparelspotter.apparel.model.neo4j.TopLikedClothOfRetailer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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
    @Query("MATCH (:Customer {_id: $customerId})-[:FOLLOWS]->(follower:Customer)-[:LIKE]->(cloth:Cloth) " +
            "WITH cloth, COUNT(*) AS likes " +
            "ORDER BY likes DESC " +
            "LIMIT 5 " +
            "RETURN cloth._id AS clothId, likes")
    List<ClothFollowLike> findTopLikedClothesByFollowers(String customerId);

@Query("MATCH (c:Cloth {_id: $id})<-[r:LIKE]-() " +
        "RETURN count(r) AS numberOfLikes")
Integer getNumberOfLikesByClothId(@Param("id") String id);

    @Query("MATCH (cloth:Cloth) WHERE cloth._id = $mongoId RETURN cloth")
    Optional<ClothNeo4j> findClothByMongoId(String mongoId);
    @Query("MATCH (c:Cloth)<-[r:LIKE]-(:Customer) " +
            "RETURN c._id AS clothId, COUNT(r) AS likeCount " +
            "ORDER BY likeCount DESC " +
            "LIMIT 5")
    List<TopLikedCloth> findTop5LikedCloths();
}
