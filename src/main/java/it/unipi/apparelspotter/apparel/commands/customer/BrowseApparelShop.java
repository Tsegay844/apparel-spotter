package it.unipi.apparelspotter.apparel.commands.customer;

import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.dto.ReviewState;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
@Component
public class BrowseApparelShop {

    private  final CustomerPage customerPage;
    private final CustomerService customerService;
    private final Scanner scanner;

    @Autowired
    public BrowseApparelShop( CustomerPage customerPage,CustomerService customerService){
        this.customerPage = customerPage;
        this.customerService=customerService;
        this.scanner=new Scanner(System.in);
    }

    String retailerIdStr="";
    String customerId= GlobalState.getInstance().getCurrentCustomerId();
    public void search() {
        int command = -1; // Initialize to something other than 0 to enter the loop
        do {
            getRandomApparel(); // Assume this method is implemented elsewhere
            try {
                System.out.println("1. Next ");
                System.out.println("2. Follow ");
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
                        System.out.println("You Followed successfully!");
                        customerService.followRetailer(customerId,retailerIdStr);
                        afterFOllow(retailerIdStr);
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


    public ResponseEntity<Void> getRandomApparel() {
        RetailerMongo randomRetailer = customerService.getRandomApparelShop();
        retailerIdStr =randomRetailer.getId();

        System.out.println("Shop ID: " + randomRetailer.getId());
        System.out.println("Shope Name: " + randomRetailer.getRetailerName());
        System.out.println("Retailer name: " + randomRetailer.getFirstName()+" "+randomRetailer.getLastName());
        System.out.println("Type: " + randomRetailer.getGender());
        System.out.println("Email: " + randomRetailer.getEmail());
        System.out.println("Contact: " + randomRetailer.getPhoneNumber());

        try {
            ReviewState reviewState = customerService.getRetailerStats(retailerIdStr);
            System.out.println("Total Number of Reviews: " + reviewState.getNumberOfReviews());
            System.out.printf("Average Rating: %.2f%n", reviewState.getAverageRating());
            System.out.println("Number of Followers: " + customerService.getNumberOfFollowersByClothId(randomRetailer.getId()));

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok().build();

    }
    public ResponseEntity<Void> afterFOllow(String retailerIdStr) {
        ObjectId retailer = new ObjectId(retailerIdStr);
        Optional<RetailerMongo> retailerOptional = customerService.findRetailerById(retailer);
        System.out.print("Updated Number of Followers: " + customerService.getNumberOfFollowersByClothId(retailerIdStr));
        retailerOptional.ifPresent(r -> System.out.println("---you are following Retailer ID: " + r.getId()));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        //System.out.println("Number of Reviews: " + customerService.getNumberOfReviewsByClothId(clothId));
        //System.out.println("Average Rating: " + customerService.getAverageRatingByClothId(clothId));
        // Return an empty response body with a 200 OK status.
        return ResponseEntity.ok().build();

    }


}

