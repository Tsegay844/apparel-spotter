package it.unipi.apparelspotter.apparel.commands.customer;

import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.Service.RetailerService;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Component
public class BrowseCloth {

    private  final CustomerPage customerPage;
    private final CustomerService customerService;
    private final Scanner scanner;

    @Autowired
    public BrowseCloth( CustomerPage customerPage,CustomerService customerService){
        this.customerPage = customerPage;
        this.customerService=customerService;
        this.scanner=new Scanner(System.in);
    }

    String clothIdString="";
    String customerId= GlobalState.getInstance().getCurrentCustomerId();
        public void search() {
            int command = -1; // Initialize to something other than 0 to enter the loop
            do {
                getRandomCloth(); // Assume this method is implemented elsewhere
                try {
                    System.out.println("1. Next ");
                    System.out.println("2. like ");
                    System.out.println("0. Abort Searching");
                    System.out.print("Enter your choice: ");
                    command = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left-over
                    switch (command) {
                        case 0:
                            System.out.println("Exiting application...");
                            break;
                        case 1:
                            break;
                        case 2:
                            String customerId= GlobalState.getInstance().getCurrentCustomerId();
                            System.out.println("After Like");
                            customerService.likeCloth(customerId,clothIdString);
                            afterLike(clothIdString);
                            break;
                        default:
                            System.out.println("Unknown command");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume the invalid input
                }
            } while (command != 0);
        }


        public ResponseEntity<Void> getRandomCloth() {
        ClothMongo randomCloth = customerService.getRandomCloth();
        clothIdString = randomCloth.getId();
            // Assuming this returns a String
        ObjectId clothId = new ObjectId(clothIdString); // Convert String to ObjectId

        System.out.println("Cloth ID: " + randomCloth.getId());
        System.out.println("Item Name: " + randomCloth.getItem_name());
        System.out.println("Category: " + randomCloth.getCategory());
        System.out.println("Type: " + randomCloth.getType());
        System.out.println("Brand: " + randomCloth.getItem_name());
        System.out.println("Image URL: " + randomCloth.getImageUrl());
        System.out.println("Price: " + randomCloth.getPrice());
        System.out.println("Number of likes: " + customerService.getNumberOfLikesByClothId(clothId));
        System.out.println("Number of Reviews: " + customerService.getNumberOfReviewsByClothId(clothId));
        System.out.println("Average Rating: " + customerService.getAverageRatingByClothId(clothId));
        System.out.println("Posted By: " + randomCloth.getRetailer().getRetailerName());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Return an empty response body with a 200 OK status.
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<Void> afterLike(String ClothId) {
        ObjectId clothId = new ObjectId(clothIdString);
        // Convert String to ObjectId
        Optional<ClothMongo> clothOptional = customerService.findClothById(clothId);
        clothOptional.ifPresent(cloth -> System.out.println("Cloth ID: " + cloth.getId()));
        System.out.println("Updated Number of likes: " + customerService.getNumberOfLikesByClothId(clothId));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        //System.out.println("Number of Reviews: " + customerService.getNumberOfReviewsByClothId(clothId));
        //System.out.println("Average Rating: " + customerService.getAverageRatingByClothId(clothId));
        // Return an empty response body with a 200 OK status.
        return ResponseEntity.ok().build();

    }


}
