package it.unipi.apparelspotter.apparel.commands;
import it.unipi.apparelspotter.apparel.Service.AuthService;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Auth {

    private final CustomerService customerService;
    private CustomerPage customerPage;

    @Autowired
    public void setCustomerPage(CustomerPage customerPage) {
        this.customerPage = customerPage;
    }

   private RetailerPage retailerPage;
    private final AuthService authService;
    private final Scanner scanner;

    @Autowired
    public Auth(AuthService authService, CustomerService customerService, @Lazy RetailerPage retailerPage) {
        this.authService = authService;
        this.customerService = customerService;
       this.retailerPage= retailerPage;
        this.scanner = new Scanner(System.in);
    }


    public void performAction() {
        firstCommand();
        int command =1;
        do {
            try {

                System.out.print("Enter your choice (1 to Log-out, 0 to exit): ");
                command = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                switch (command) {
                    case 0:
                        System.out.println("Exiting application...");
                        break;
                    case 1:
                        performAction();
                        break;
                    default:
                        System.out.println("Unknown command");
                        performAction();
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
        System.out.println();
        System.out.println("\u001B[1mHome Page\u001B[0m");
        System.out.println("**************************************");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Exit");
        System.out.print("Enter your choice (0 to exit): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch (choice) {
            case 1:
                System.out.println();
                System.out.println("\u001B[1mLog-in page\u001B[0m");
                System.out.println("**************************************");
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
                System.out.println();
                System.out.println("\u001B[1mSign-up process would be here.\u001B[0m");
                System.out.println("*****************************************");
                signup();
                break;
            case 0:
                System.out.println("Exiting the application...");
                scanner.close(); // close the scanner before exiting
                System.exit(0); // terminate the JVM
                break;
            default:
                System.out.println("\u001B[31mWrong input\u001B[0m");
                performAction();
                break;
        }

    }

    public void signup(){
        System.out.println("1. Customer");
        System.out.println("2. Retailer");
        System.out.println("3. Back to Home page");
        System.out.println("0. Exit");
        System.out.print("Enter your choice (0 to exit): ");
        int option;
        option=scanner.nextInt();
        scanner.nextLine();
        if (option==0) {
            System.out.println("Exiting the application...");
            scanner.close();
            System.exit(0);
        }
        else if (option==3) {
            performAction();
        }
        else if (option==1 || option==2) {
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

            }
        }
        else {
            System.out.println("\u001B[31mWrong input\u001B[0m");
            signup();


        }
    }


    // Be sure to include proper closing and exception handling in your application
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}