package it.unipi.apparelspotter.apparel.commands.retailer;

import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Service.ClothService;
import it.unipi.apparelspotter.apparel.model.mongo.CategoryCount;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerAverageRating;
import it.unipi.apparelspotter.apparel.model.neo4j.TopLikedClothOfRetailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Component
public class Statistics {
    private  final ClothService clothService;
    private final Scanner scanner;

    @Autowired
    public Statistics( ClothService clothService){
        this.clothService=clothService;
        this.scanner=new Scanner(System.in);
    }
    public void numberOfCloths(){
        String retailerId = GlobalState.getInstance().getCurrentRetailerId();
        System.out.println("Retailer ID: " + retailerId);
        Long numberOfCloths=clothService.getNumberOfClothsForRetailer(retailerId);
        System.out.print("\u001B[32m=>\u001B[0m");
        System.out.print("Total Number of cloths posted: "+numberOfCloths);
    }
    public void averageRating(){
        String retailerId = GlobalState.getInstance().getCurrentRetailerId();
        Optional<RetailerAverageRating> averageRatingResult = clothService.getAverageRatingForLoggedInRetailer(retailerId);
        if (averageRatingResult.isPresent()) {
            double averageRating = averageRatingResult.get().getAverageRating();
            System.out.print("\u001B[32m=>\u001B[0m");
            System.out.println("Average Rating: " + averageRating);
        } else {
            System.out.println("No ratings found for retailer with ID: " + retailerId);
        }
    }
    public void printClothsCountByCategory() {
        String retailerId = GlobalState.getInstance().getCurrentRetailerId();


        List<CategoryCount> categoryCounts = clothService.getClothsCountByCategoryForLoggedInRetailer(retailerId);

        if (categoryCounts.isEmpty()) {
            System.out.println("No cloths found for the retailer.");
        } else {
            System.out.print("\u001B[32m=>\u001B[0m");
            System.out.println("Number of cloths by category:");
            for (CategoryCount categoryCount : categoryCounts) {
                System.out.print("  \u001B[31m-\u001B[0m");
                System.out.printf(" %s, Count: %d%n", categoryCount.getId(), categoryCount.getCount());
            }
        }
    }
    public void topLikedClothsOfRetailer() {
        // Assuming GlobalState and clothService are available and properly initialized
        String retailerId = GlobalState.getInstance().getCurrentRetailerId().trim();
        List<TopLikedClothOfRetailer> topCloths = clothService.getTop5ClothsByLikesForRetailer(retailerId);
        if (topCloths.isEmpty()) {
            System.out.println("No cloths found for the retailer with ID: " + retailerId);
        } else {
            System.out.print("\u001B[32m=>\u001B[0m");
            System.out.println("Top 5 Liked cloths: ");
            topCloths.forEach(cloth -> System.out.println("  \u001B[31m-\u001B[0m"+"Cloth_id: " + cloth.getClothId() + ", Likes: " + cloth.getLikes()));
        }
    }

    public void totalLikesOfClothsByRetailer() {
        // Assuming GlobalState and clothService are available and properly initialized
        String retailerId = GlobalState.getInstance().getCurrentRetailerId().trim();
       int numberOflikes= clothService.getTotalLikesForRetailer(retailerId);
        System.out.print("\u001B[32m=>\u001B[0m");
        System.out.println("Total Number of Likes of your cloths: " + numberOflikes);

    }
    public void totalFollowersByRetailer() {
        String retailerId = GlobalState.getInstance().getCurrentRetailerId().trim();
        int numberOfFollowers= clothService.getToltalFollowersForRetailer(retailerId);
        System.out.print("\u001B[32m=>\u001B[0m");
        System.out.println("Total Number of Followers: " + numberOfFollowers);

    }

}
