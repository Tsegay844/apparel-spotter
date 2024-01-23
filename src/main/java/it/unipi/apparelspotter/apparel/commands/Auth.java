package it.unipi.apparelspotter.apparel.commands;

import it.unipi.apparelspotter.apparel.Service.AuthService;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Auth {

    private final CustomerService customerService;
    private final CustomerPage customerPage;
   private final RetailerPage retailerPage;
    private final AuthService authService;
    private final Scanner scanner;

    @Autowired
    public Auth(AuthService authService, CustomerService customerService, CustomerPage customerPage, RetailerPage retailerPage) {
        this.authService = authService;
        this.customerService = customerService;
        this.customerPage = customerPage;
       this.retailerPage= retailerPage;
        this.scanner = new Scanner(System.in);
    }

    public void performAction() {
        System.out.println("Action performed!");
        firstCommand();
        int command =1;
        do {
            try {
                /*System.out.println("1. getAllMongoCustomers ");
                System.out.println("2. getMongoCustomerById");
                System.out.println("3. getAllNeo4jCustomers");
                System.out.println("4. getNeo4jCustomerById");*/
                System.out.println("1. back");
                System.out.println("0. Exit");
                System.out.print("Enter your choice (0 to exit): ");

                command = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (command) {
                    case 5:
                        customerService.getAllMongoCustomers().forEach(System.out::println);
                        break;

                    case 2:
                        System.out.print("Enter Id: ");
                        String customerId = scanner.nextLine();
                        Optional<CustomerMongo> customerOptional = customerService.getMongoCustomerById(customerId);
                        customerOptional.ifPresentOrElse(
                                customer -> System.out.println("Customer Name: " + customer.getFirstName() + " " + customer.getLastName()),
                                () -> System.out.println("Customer not found.")
                        );
                        break;

                    case 3:
                        customerService.getAllNeo4jCustomers().forEach(System.out::println);
                        break;

                    case 4:
                        System.out.print("Enter Id: ");
                        String id = scanner.nextLine();
                        // Assuming there is a similar method for Neo4j customers
                        Optional<CustomerMongo> neoCustomerOptional = customerService.getMongoCustomerById(id);
                        neoCustomerOptional.ifPresentOrElse(
                                customer -> System.out.println("Customer Name: " + customer.getFirstName() + " " + customer.getLastName()),
                                () -> System.out.println("Customer not found.")
                        );
                        break;

                    case 0:
                        System.out.println("Exiting application...");
                        break;
                    case 1:
                        performAction();
                        break;

                    default:
                        System.out.println("Unknown command");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                performAction();// Consume the invalid input
            }
        } while (command != 0);
    }

    public void firstCommand() {
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Exit");
        System.out.print("Enter your choice (0 to exit): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch (choice) {
            case 1:
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                Optional<CustomerMongo> customerOptional = authService.customerLogin(email, password);
                Optional<RetailerMongo> retailerOptional1 = authService.retailerLogin(email);
                /*retailerOptional.ifPresentOrElse(
                        retailer -> {
                            String oldPassword = retailer.getPassword();
                            if(oldPassword==password){
                                customerPage.customerpage();
                                System.out.println("newPassword: " + password);
                                System.out.println("OldPassword: " + oldPassword);
                            }
                        },
                        () -> {
                            System.out.println("Retailer not found.");
                        }
                );*/
                Optional<RetailerMongo> retailerOptional = authService.retailerLogin(email);
                if (retailerOptional.isPresent()) {
                    System.out.println("Retailer login successful!");
                    retailerPage.retailerPage();

                }
                else if (customerOptional.isPresent()) {
                    System.out.println("Customer login successful!");
                   customerPage.customerpage();
                } else {
                    System.out.println("Login failed. Invalid email or password.");
                }
                break;
            case 2:
                // Sign-up logic here, assuming a method exists for it
                System.out.println("Sign-up process would be here.");
                signup();
                break;
            case 0:
                System.out.println("Exiting application...");
                break;
            default:
                System.out.println("Unknown command");
                break;
        }

    }

    public void signup(){
        System.out.println("1. Customer");
        System.out.println("2. Retailer");
        System.out.println("3. Back to login");
        int option;
        option=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter firstName: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter lastName: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter contact: ");
        int contact = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (option == 1) {
            authService.customerSignup(firstName, lastName, gender, contact, email, password);

        } else if (option==2) {
            System.out.print("Enter Retailer Name: ");
            String retairelName = scanner.nextLine();
            authService.retailerSignup(firstName, lastName, gender, contact, email, password, retairelName);

        } else if (option==3) {
            performAction();

        }

    }


    // Be sure to include proper closing and exception handling in your application
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}