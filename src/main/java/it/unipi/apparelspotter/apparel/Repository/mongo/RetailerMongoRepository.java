package it.unipi.apparelspotter.apparel.Repository.mongo;

import it.unipi.apparelspotter.apparel.Repository.neo4j.RetailerNeo4jRepository;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface RetailerMongoRepository extends MongoRepository <RetailerMongo, String> {
    @Query("{ 'email' : ?0, 'password' : ?1 }")
    Optional<RetailerMongo> findByEmailAndPassword(String email, String password);
    Optional<RetailerMongo>findByEmail(String email);
    Optional<RetailerMongo>findByPassword(String password);

}
