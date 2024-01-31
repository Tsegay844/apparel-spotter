package it.unipi.apparelspotter.apparel.commands.retailer;

import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Repository.mongo.RetailerMongoRepository;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class ProfileRtailer {
    private  final RetailerPage retailerPage;
    private  final CustomerService customerService;
    private RetailerMongoRepository retailerMongoRepository;
    @Autowired
    public ProfileRtailer(RetailerMongoRepository retailerMongoRepository, RetailerPage retailerPage, CustomerService customerService){
        this.retailerPage=retailerPage;
        this.customerService=customerService;
        this.retailerMongoRepository=retailerMongoRepository;
    }
    public void editProfile() {
        String retailerId = GlobalState.getInstance().getCurrentRetailerId();
        Optional<RetailerMongo>currentRetailer = customerService.findRetailerByObId(new ObjectId(retailerId));
        Scanner scanner = new Scanner(System.in);
        if (currentRetailer.isPresent()) {
            RetailerMongo retailer = currentRetailer.get();
            System.out.println("Editing Retailer ProfileRtailer:");
            System.out.println("Choose a field to edit:");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Gender:");
            System.out.println("4. email");
            System.out.println("5. Password");
            System.out.println("6. Phone Number");
            System.out.print("Choose a field to edit:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    System.out.print("Enter new first name: ");
                    retailer.setLastName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new last name: ");
                   retailer.setLastName(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new gender: ");
                    retailer.setGender(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new email: ");
                    retailer.setEmail(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter new password: ");
                    retailer.setPassword(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter new phone number: ");
                    retailer.setPhoneNumber(scanner.nextLine());
                    break;
                default:
                    System.out.println("\u001B[31mWrong input\u001B[0m");
                    editProfile();
                    break;
            }
            RetailerMongo retailerMongo= retailerMongoRepository.save(retailer);
            System.out.println("ProfileRtailer updated successfully!");
        }

    }
    public void viewProfile() {
        String retailerId = GlobalState.getInstance().getCurrentRetailerId();
        ObjectId retailerId1 = new ObjectId(retailerId);
        Optional<RetailerMongo> optionalRetailerMongo = retailerMongoRepository.findById(retailerId1);
        if (optionalRetailerMongo.isPresent()) {
            RetailerMongo currentRetailer = optionalRetailerMongo.get();
            System.out.println("--------------------------------");
            System.out.println("1. First Name: " + currentRetailer.getFirstName());
            System.out.println("2. Last Name: " + currentRetailer.getLastName());
            System.out.println("3. Gender: " + currentRetailer.getGender());
            System.out.println("4. Email: " + currentRetailer.getEmail());
            System.out.println("5. Password: " + currentRetailer.getPassword()); // Caution: Consider security implications
            System.out.println("6. Phone Number: " + currentRetailer.getPhoneNumber());
            System.out.println("7. Longitude: " + currentRetailer.getLongitude());
            System.out.println("8. Latitude: " + currentRetailer.getLatitude());
        } else {
            System.out.println("profile error");
        }
    }

}
