package it.unipi.apparelspotter.apparel.commands;

import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Service.ClothService;
import it.unipi.apparelspotter.apparel.model.mongo.CategoryCount;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerAverageRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
        System.out.print("Total Number of cloths you posted: "+numberOfCloths);
    }
    public void averageRating(){
        String retailerId = GlobalState.getInstance().getCurrentRetailerId();
        Optional<RetailerAverageRating> averageRatingResult = clothService.getAverageRatingForLoggedInRetailer(retailerId);
        if (averageRatingResult.isPresent()) {
            double averageRating = averageRatingResult.get().getAverageRating();
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
            System.out.println("Number of cloths by category:");
            for (CategoryCount categoryCount : categoryCounts) {
                System.out.printf(" %s, Count: %d%n", categoryCount.getId(), categoryCount.getCount());
            }
        }
    }
}
