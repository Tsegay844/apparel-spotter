package it.unipi.apparelspotter.apparel.Repository.mongo;

import it.unipi.apparelspotter.apparel.Repository.neo4j.RetailerNeo4jRepository;
import it.unipi.apparelspotter.apparel.model.dot.ReviewState;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import it.unipi.apparelspotter.apparel.model.neo4j.RetailerNeo4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RetailerMongoRepository extends MongoRepository <RetailerMongo, String> {
    Optional<RetailerMongo> findRetailerById(ObjectId id);
    @Query("{ 'email' : ?0, 'password' : ?1 }")
    Optional<RetailerMongo> findByEmailAndPassword(String email, String password);
    Optional<RetailerMongo>findByEmail(String email);
    Optional<RetailerMongo>findByPassword(String password);
    @Query("{ 'id': { $in: ?0 } }")
    List<RetailerMongo> findByIdIn(List<String> ids);

    @Aggregation(pipeline = { "{ $sample: { size: 1 } }" })
    Optional<RetailerMongo> findRandomApparel();




}





