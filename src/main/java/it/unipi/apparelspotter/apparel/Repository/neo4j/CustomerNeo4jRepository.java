package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.neo4j.CustomerNeo4j;
import it.unipi.apparelspotter.apparel.model.neo4j.RetailerNeo4j;
import org.springframework.data.neo4j.repository.query.Query; // Corrected import
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerNeo4jRepository extends Neo4jRepository<CustomerNeo4j, String> {

    @Query("MATCH (c:CustomerNeo4j) RETURN c")
    List<CustomerNeo4j> findAllCustomers();

    @Query("MATCH (customer:Customer) RETURN id(customer) as internalId, customer")
    List<Map<String, Object>> findAllCustomerIds();

    @Query("MATCH (customer:Customer {_id: $mongoId}) RETURN customer")
    Optional<CustomerNeo4j> findCustomerByMongoId(String mongoId);

    @Query("MATCH (c:Customer {id: $id}) RETURN c")
    Optional<CustomerNeo4j> findCustomerByPropertyId(@Param("id") String propertyId);


    @Query("MATCH (customer:Customer)-[:LIKE]->(cloth:Cloth) " +
            "WHERE customer._id = $customerId " +
            "WITH cloth, SIZE([(cloth)<-[:LIKE]-() | 1]) AS likes " +
            "ORDER BY likes DESC " +
            "LIMIT 5 " +
            "RETURN cloth._id AS clothId")
    List<String> findTopLikedClothsByCustomerId(@Param("customerId") String customerId);

    @Query("MATCH (customer:Customer { _id: $customerId })-[:FOLLOW]->(retailer:Retailer) " +
            "WITH retailer " +
            "MATCH (retailer)<-[:FOLLOW]-(otherCustomer:Customer) " +
            "WITH retailer, COUNT(DISTINCT otherCustomer) AS followersCount " +
            "ORDER BY followersCount DESC " +
            "LIMIT 5 " +
            "MATCH (retailer)<-[:POSTED_BY]-(cloth:Cloth) " +
            "WITH retailer, cloth, followersCount " +
            "MATCH (cloth)<-[:LIKE]-(anyone:Customer) " +
            "WITH retailer, cloth, followersCount, COUNT(DISTINCT anyone) AS likesCount " +
            "ORDER BY likesCount DESC " +
            "WITH retailer, followersCount, COLLECT(cloth)[0] AS topCloth " +
            "RETURN topCloth._id AS topClothId")
    List<String> findTopClothsForTopRetailers(String customerId);
    @Query("MATCH (c:Customer {_id: $id}) DETACH DELETE c")
    void deleteCustomerByPropertyId(@Param("id") String propertyId);

}