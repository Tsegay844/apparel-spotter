package it.unipi.apparelspotter.apparel.Repository.mongo;

import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClothMongoRepository extends MongoRepository<ClothMongo, String> {
    Optional<ClothMongo> findById(String id);



}
