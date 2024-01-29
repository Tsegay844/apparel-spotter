package it.unipi.apparelspotter.apparel.commands.retailer;

import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Repository.mongo.ClothMongoRepository;
import it.unipi.apparelspotter.apparel.Service.RetailerService;
import it.unipi.apparelspotter.apparel.model.dot.ClothObjectId;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@Component
public class ClothList {

    private  final RetailerService retailerService;
    private final ClothMongoRepository clothMongoRepository;
    private final Scanner scanner;
    @Autowired
    public ClothList(RetailerService retailerService, ClothMongoRepository clothMongoRepository){
        this.retailerService = retailerService;
        this.clothMongoRepository=clothMongoRepository;
        this.scanner=new Scanner(System.in);
    }
    public void printRandomClothsByRetailer() {
        String retailerId = GlobalState.getInstance().getCurrentRetailerId();
        List<ClothObjectId> clothIds = retailerService.getRandomClothsByRetailerId(retailerId);

        for (ClothObjectId clothObjectId : clothIds) {
            ObjectId id = clothObjectId.getClothId();
            System.out.println("Cloth ID: " + id.toString());


            Double averageRating = retailerService.getAverageRatingByClothId(id); // Make sure this exists
            Integer numberOfReviews = retailerService.getNumberOfReviewsByClothId(id); // Make sure this exists
            Integer numberOfLikes = retailerService.getNumberOfLikesByClothId(id);
            //String category = retailerService.getClothCategory(id);
           // System.out.println("Cloth Category: " + category);
            System.out.println("Number of Likes: " + (numberOfLikes != null ? numberOfLikes : "Not available"));
            System.out.println("Number of Reviews: " + (numberOfReviews != null ? numberOfReviews : "Not available"));
            System.out.println("Average Rating: " + (averageRating != null ? String.format("%.2f", averageRating) : "Not available"));
            System.out.println("_____________________________________________________________________________________________________________");
        }
    }
    }


