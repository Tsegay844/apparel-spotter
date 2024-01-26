package it.unipi.apparelspotter.apparel.Service;
import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Repository.mongo.ClothMongoRepository;
import it.unipi.apparelspotter.apparel.Repository.neo4j.ClothNeo4jRepository;
import it.unipi.apparelspotter.apparel.Repository.neo4j.RetailerNeo4jRepository;
import it.unipi.apparelspotter.apparel.model.mongo.CategoryCount;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerAverageRating;
import it.unipi.apparelspotter.apparel.model.neo4j.ClothNeo4j;
import it.unipi.apparelspotter.apparel.model.neo4j.RetailerNeo4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClothService {
    private final ClothMongoRepository clothMongoRepository;
    private final ClothNeo4jRepository clothNeo4jRepository;
    private final RetailerNeo4jRepository retailerNeo4jRepository;
    private final MongoTemplate mongoTemplate;
    public ClothService( MongoTemplate mongoTemplate, RetailerNeo4jRepository retailerNeo4jRepository, ClothMongoRepository clothMongoRepository, ClothNeo4jRepository clothNeo4jRepository){
        this.clothMongoRepository=clothMongoRepository;
        this.clothNeo4jRepository=clothNeo4jRepository;
        this.retailerNeo4jRepository=retailerNeo4jRepository;
        this.mongoTemplate = mongoTemplate;
    }
    String clothId=null;
    String retailerId = GlobalState.getInstance().getCurrentRetailerId();
           public ClothMongo postCloth(String category, String type, String size, String brand, String price, String url, Date postDate) {
            ClothMongo newCloth= new ClothMongo();
            newCloth.setCategory(category);
            newCloth.setType(type);
            newCloth.setSize(size);
            newCloth.setBrand(brand);
            newCloth.setPrice(price);
            newCloth.setImageUrl(url);
            newCloth.setPostedDate(postDate);
            ClothMongo savedCloth = clothMongoRepository.save(newCloth);
            System.out.println("Saved cloth: " + savedCloth.toString());
            System.out.println("Cloth posting successful!");
            clothId = savedCloth.getId();
            postClothNode();
            return savedCloth;
            }
    public void postClothNode() {

        if (clothId == null) {
            throw new IllegalStateException("Cloth ID must be set before calling postClothNode.");
        }
        ClothNeo4j newClothNode = new ClothNeo4j();
        newClothNode.setMongoId(clothId);
        String retailerId = GlobalState.getInstance().getCurrentRetailerId();
        RetailerNeo4j retailer = retailerNeo4jRepository.findByMongoId(retailerId)
                .orElseThrow(() -> new NoSuchElementException("Retailer with _id " + retailerId + " not found."));
        newClothNode.setPostedByRetailer(retailer);
        ClothNeo4j savedClothNode = clothNeo4jRepository.save(newClothNode);
        System.out.println("Saved node: " + savedClothNode.toString());
        System.out.println("Node creation successful!");
    }
    public long getNumberOfClothsForRetailer(String retailerId) {
        if (retailerId == null || retailerId.trim().isEmpty()) {
            throw new IllegalArgumentException("retailerId cannot be null or empty");
        }
        Query query = new Query(Criteria.where("retailerId").is(new ObjectId(retailerId)));
        return mongoTemplate.count(query, ClothMongo.class);
    }
    public Optional<RetailerAverageRating> getAverageRatingForLoggedInRetailer(String loggedInRetailerId) {
        return clothMongoRepository.getAverageRatingForRetailer(loggedInRetailerId);
    }
    public Optional<RetailerAverageRating> getAverageRatingOFLoggedInRetailer(String retailerId) {
        String loggedInRetailerId = GlobalState.getInstance().getCurrentRetailerId();
        return clothMongoRepository.getAverageRatingForRetailer(loggedInRetailerId);
    }
    public List<CategoryCount> getClothsCountByCategoryForLoggedInRetailer(String retailerId) {
        return clothMongoRepository.countClothsByCategoryForRetailer(retailerId);
    }
}







