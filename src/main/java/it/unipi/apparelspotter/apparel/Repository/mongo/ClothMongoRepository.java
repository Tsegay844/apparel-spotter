package it.unipi.apparelspotter.apparel.Repository.mongo;

import it.unipi.apparelspotter.apparel.model.mongo.CategoryCount;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerAverageRating;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClothMongoRepository extends MongoRepository<ClothMongo, String>, ClothMongoRepositoryCustom  {
    Optional<ClothMongo> findById(String id);
    @Query(value = "{'retailer._id': ?0}", count = true)
    long countByRetailerId(ObjectId retailerId);

    @Aggregation(pipeline = {
            "{ $match: { 'retailer._id': ?0 } }",
            "{ $unwind: '$Reviews' }",
            "{ $group: { _id: '$retailer._id', averageRating: { $avg: '$Reviews.Rating' } } }"
    })
    Optional<RetailerAverageRating> getAverageRatingForRetailer(String retailerId);
    @Aggregation(pipeline = {
            "{ $match: { 'retailer._id': ?0 } }",
            "{ $group: { _id: '$Category', count: { $sum: 1 } } }"
    })
    List<CategoryCount> countClothsByCategoryForRetailer(String retailerId);



}
