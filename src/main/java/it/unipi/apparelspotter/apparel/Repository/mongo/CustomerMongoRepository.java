package it.unipi.apparelspotter.apparel.Repository.mongo;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface CustomerMongoRepository extends MongoRepository<CustomerMongo, String> {
    Optional<CustomerMongo> findByEmailAndPassword(String email, String password);
    Optional<CustomerMongo> findByEmail(String email);
    Optional<CustomerMongo> findById(ObjectId id);
    Long deleteByEmail(String email);


}