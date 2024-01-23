package it.unipi.apparelspotter.apparel.Repository.mongo;

import it.unipi.apparelspotter.apparel.model.mongo.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewMongoRepository extends MongoRepository<ReviewMongo, String> {

}
