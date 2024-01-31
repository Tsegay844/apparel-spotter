package it.unipi.apparelspotter.apparel.commands.customer;

import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Repository.mongo.CustomerMongoRepository;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Profile {
    private  final CustomerPage customerPage;
    private  final CustomerService customerService;
    private CustomerMongoRepository customerMongoRepository;
    @Autowired
    public Profile(CustomerMongoRepository customerMongoRepository, CustomerPage customerPage, CustomerService customerService){
        this.customerPage=customerPage;
        this.customerService=customerService;
        this.customerMongoRepository=customerMongoRepository;
    }

    public void editProfile() {
        String customerId = GlobalState.getInstance().getCurrentCustomerId();
        Optional<CustomerMongo>currentCustomer = customerService.findCustomerById(new ObjectId(customerId));
        Scanner scanner = new Scanner(System.in);
        if (currentCustomer.isPresent()) {

            CustomerMongo customer = currentCustomer.get();
            System.out.println("Editing Customer ProfileRtailer:");
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
                    customer.setLastName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new last name: ");
                    customer.setLastName(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new gender: ");
                    customer.setGender(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new email: ");
                    customer.setEmail(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter new password: ");
                    customer.setPassword(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter new phone number: ");
                    customer.setPhoneNumber(scanner.nextLine());
                    break;
                default:
                    System.out.println("\u001B[31mWrong input\u001B[0m");
                    editProfile();
                    break;
            }
            CustomerMongo customerMongo= customerMongoRepository.save(customer);
            System.out.println("ProfileRtailer updated successfully!");
        }

    }
    public void viewProfile() {
        String customerId = GlobalState.getInstance().getCurrentCustomerId();
        ObjectId customerId1 = new ObjectId(customerId);
        Optional<CustomerMongo> optionalCustomerMongo = customerMongoRepository.findById(customerId1);
        if (optionalCustomerMongo.isPresent()) {
            CustomerMongo currentCustomer = optionalCustomerMongo.get();
            System.out.println("--------------------------------");
            System.out.println("1. First Name: " + currentCustomer.getFirstName());
            System.out.println("2. Last Name: " + currentCustomer.getLastName());
            System.out.println("3. Gender: " + currentCustomer.getGender());
            System.out.println("4. Email: " + currentCustomer.getEmail());
            System.out.println("5. Password: " + currentCustomer.getPassword()); // Caution: Consider security implications
            System.out.println("6. Phone Number: " + currentCustomer.getPhoneNumber());
            System.out.println("7. Longitude: " + currentCustomer.getLongitude());
            System.out.println("8. Latitude: " + currentCustomer.getLatitude());
        } else {
            System.out.println("Customer profile not found.");
        }
    }

}
