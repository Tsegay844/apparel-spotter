package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.neo4j.CustomerNeo4j;
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
    Optional<CustomerNeo4j> findCustomerByMongoId(@Param("mongoId") String mongoId);

}