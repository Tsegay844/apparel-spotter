package it.unipi.apparelspotter.apparel.Repository.mongo;

import it.unipi.apparelspotter.apparel.model.mongo.RetailerAverageRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class ClothMongoRepositoryCustomImpl implements ClothMongoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ClothMongoRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public List<RetailerAverageRating> getAverageRatingForEachRetailer() {
        Aggregation aggregation = newAggregation(
                unwind("reviews"),
                group("retailer._id").avg("reviews.rating").as("averageRating"),
                project("averageRating").and("retailerId").previousOperation()
        );

        AggregationResults<RetailerAverageRating> results = mongoTemplate.aggregate(
                aggregation, "clothMongo", RetailerAverageRating.class
        );

        return results.getMappedResults();
    }

}
