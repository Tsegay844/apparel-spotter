package it.unipi.apparelspotter.apparel.Repository.mongo;

import it.unipi.apparelspotter.apparel.model.dto.*;
import it.unipi.apparelspotter.apparel.model.mongo.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClothMongoRepository extends MongoRepository<ClothMongo, String> {
    Optional<ClothMongo> findById(ObjectId id);

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

    @Aggregation(pipeline = {
            "{ $match: { 'retailer._id': ?0 } }",
            "{ $sample: { size: 5 } }",
            "{ $project: { _id: 1 } }"
    })
    List<ClothObjectId> findRandomClothsByRetailerId(ObjectId retailerId);

    @Aggregation(pipeline = {
            "{ $match: { '_id': ?0 } }",
            "{ $unwind: '$Reviews' }",
            "{ $group: { _id: '$_id', averageRating: { $avg: '$Reviews.Rating' } } }"
    })
    Double getAverageRatingByClothId(ObjectId clothId);

    @Aggregation(pipeline = {
            "{ $match: { '_id': ?0 } }",
            "{ $project: { numberOfReviews: { $size: '$Reviews' } } }"
    })
    Integer getNumberOfReviewsByClothId(ObjectId clothId);

    @Aggregation(pipeline = { "{ $sample: { size: 1 } }" })
    Optional<ClothMongo> findRandomCloth();
    @Aggregation(pipeline = {
            "{ $match: { 'retailer._id': ?0 } }",
            "{ $unwind: '$Reviews' }",
            "{ $group: { _id: null, averageRating: { $avg: '$Reviews.Rating' }, numberOfReviews: { $sum: 1 } } }"
    })
    Optional<ReviewState> getAverageRatingAndReviewRetailer(ObjectId retailerId);


    @Aggregation(pipeline = {
            "{ $match: { 'Reviews.0': { $exists: true } } }",
            "{ $addFields: { 'avgRating': { $avg: '$Reviews.Rating' }, 'reviewCount': { $size: { $ifNull: ['$Reviews', []] } } } }",
            "{ $match: { 'avgRating': { $gt: 4 } } }",
            "{ $sort: { 'reviewCount': -1 } }",
            "{ $limit: 5 }",
            "{ $project: { '_id': 1, 'avgRating': 1, 'reviewCount': 1 } }" // Include fields in the project stage for DTO
    })
    List<ClothIds> findTopRatedClothes();
    @Aggregation(pipeline = {
            "{ $unwind: '$Reviews' }",
            "{ $match: { 'Reviews.Rating': { $gt: 4 } } }",
            "{ $group: { _id: '$Brand', averageRating: { $avg: '$Reviews.Rating' }, reviewCount: { $sum: 1 } } }",
            "{ $match: { averageRating: { $gt: 4 } } }",
            "{ $project: { brand: '$_id', averageRating: 1, reviewCount: 1 } }", // Rename _id to brand
            "{ $sort: { reviewCount: -1 } }",
            "{ $limit: 5 }"
    })
    List<BrandPopularity> findPopularBrands();

}

