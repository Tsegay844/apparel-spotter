package it.unipi.apparelspotter.apparel.commands.customer;
import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Repository.mongo.ClothMongoRepository;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.dot.ClothIds;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Component
public class Suggestions {
    private  final CustomerPage customerPage;
    private final CustomerService customerService;
    private final Scanner scanner;
    @Autowired
    public Suggestions( CustomerPage customerPage,CustomerService customerService){
        this.customerPage = customerPage;
        this.customerService=customerService;
        this.scanner=new Scanner(System.in);
    }
    public void suggestedByLikes(){
        String customerId= GlobalState.getInstance().getCurrentCustomerId();
        List<String> topLikedCloths = customerService.findTopLikedClothsByCustomerId(customerId);
        List<String> topClothsForTopRetailers = customerService.findTopLikedClothsByCustomerId(customerId);
        if (topLikedCloths.isEmpty()) {
            System.out.println("No liked cloths found for customer with ID: " + customerId);
        } else {
            System.out.println("................................................................");
            System.out.println("Like suggestions " + customerId + ":");
            System.out.println("................................................................");
            topLikedCloths.forEach(clothId -> ClothProfile(clothId));
        }
    }
    public void suggestedByFollow(){
        String customerId= GlobalState.getInstance().getCurrentCustomerId();
        List<String> topClothsForTopRetailers = customerService.findTopClothsForTopRetailers(customerId);
        if (topClothsForTopRetailers .isEmpty()) {
            System.out.println("No liked cloths found for customer with ID: " + customerId);
        } else {
            System.out.println("................................................................");
            System.out.println("folllow suggetions" + customerId + ":");
            System.out.println("................................................................");
            topClothsForTopRetailers .forEach(clothId -> ClothProfile(clothId));
        }
   }
    public void suggestedByReviewAndRating(){
        List<ClothIds> topRatedAndReviewClothes = customerService.findTopRatedAndReviewClothes();
        System.out.println("..............................................................");
        System.out.println("Top Rated Clothes with Avg Ratings and Review Count:");
        System.out.println("................................................................");
        for (ClothIds cloth : topRatedAndReviewClothes) {
           String clothIdStr = cloth.getId();
            ClothProfiler(clothIdStr);
    }
        }
//    public void suggestedByFollow() {
//        String customerId = GlobalState.getInstance().getCurrentCustomerId();
//        List<String> topClothsForTopRetailers = customerService.findTopClothsForTopRetailers(customerId);
//
//        System.out.println("..............................................................");
//        if (topClothsForTopRetailers.isEmpty()) {
//            System.out.println("No liked cloths found for customer with ID: " + customerId);
//        } else {
//            System.out.println("Top cloths for top retailers of customer with ID " + customerId + ":");
//            System.out.println("................................................................");
//            for (String clothId : topClothsForTopRetailers) {
//                ClothProfile(clothId);
//            }
//        }
//        System.out.println("..............................................................");
//    }
public void ClothProfile(String clothIdStr){
    ObjectId clothId = new ObjectId(clothIdStr);
    Optional<ClothMongo> clothOptional = customerService.findClothById(clothId);
    clothOptional.ifPresent(clothDetails -> System.out.println("Cloth ID: " + clothDetails.getId()));
    clothOptional.ifPresent(clothDetails -> System.out.println("Cloth Name: " + clothDetails.getItem_name()));
    clothOptional.ifPresent(clothDetails -> System.out.println("Category: " + clothDetails.getCategory()));
    clothOptional.ifPresent(clothDetails -> System.out.println("Type: " + clothDetails.getType()));
    clothOptional.ifPresent(clothDetails -> System.out.println("Brand: " + clothDetails.getBrand()));
    clothOptional.ifPresent(clothDetails -> System.out.println("Size: " + clothDetails.getSize()));
    clothOptional.ifPresent(clothDetails -> System.out.println("Image URL: " + clothDetails.getImageUrl()));
    clothOptional.ifPresent(clothDetails -> System.out.println("Price: " + clothDetails.getPrice()));
    System.out.println("Number of likes: " + customerService.getNumberOfLikesByClothId(clothId));
    System.out.println("Number of Reviews: " + customerService.getNumberOfReviewsByClothId(clothId));
    System.out.printf("Average Rating: %.2f%n", customerService.getAverageRatingByClothId(clothId));
    clothOptional.ifPresent(clothDetails -> System.out.println("Posted By: " + clothDetails.getRetailer().getRetailerName()));
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
}
    public void ClothProfiler(String clothIdStr){
        ObjectId clothId = new ObjectId(clothIdStr);
        Optional<ClothMongo> clothOptional = customerService.findClothById(clothId);
        clothOptional.ifPresent(clothDetails -> System.out.println("Cloth ID: " + clothDetails.getId()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Cloth Name: " + clothDetails.getItem_name()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Category: " + clothDetails.getCategory()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Type: " + clothDetails.getType()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Brand: " + clothDetails.getBrand()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Size: " + clothDetails.getSize()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Image URL: " + clothDetails.getImageUrl()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Price: " + clothDetails.getPrice()));
        System.out.println("Number of likes: " + customerService.getNumberOfLikesByClothId(clothId));
        System.out.println("Number of Reviews: " + customerService.getNumberOfReviewsByClothId(clothId));
        System.out.printf("Average Rating: %.2f%n", customerService.getAverageRatingByClothId(clothId));
        clothOptional.ifPresent(clothDetails -> System.out.println("Posted By: " + clothDetails.getRetailer().getRetailerName()));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}




