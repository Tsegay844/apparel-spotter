package it.unipi.apparelspotter.apparel.commands;
import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Service.AuthService;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
    //Home page Or log-in page starts from this method
    public void performAction() {
        firstCommand();
        int command =1;
        do {
            try {
                System.out.print("Enter your choice (1 to Log-out, 0 to exit): ");
                command = scanner.nextInt();
                scanner.nextLine();
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
                performAction();
            }
        } while (command != 0);
    }

    //********************LOG_IN****************************
    public void firstCommand() {
        System.out.println();
        System.out.println("\u001B[1mHome Page\u001B[0m");
        System.out.println("**************************************");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Exit");
        System.out.print("Enter your choice (0 to exit): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println();
                System.out.println("\u001B[1mLog-in page\u001B[0m");
                System.out.println("**************************************");
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                Optional<CustomerMongo> customerOptional = authService.customerLogin(email, password);;
                Optional<RetailerMongo> retailerOptional = authService.retailerLogin(email);
                if (retailerOptional.isPresent()) {
                    System.out.println("Retailer login successful!");
                    //String retailerId = retailerOptional.get().getId();
                    //GlobalState.getInstance().setCurrentRetailerId(retailerId);
                    // *****************Go to Retailer Page****************************
                    RetailerMongo retailer = retailerOptional.get();
                    String retailerId = retailer.getId();
                    if (retailerId == null || retailerId.trim().isEmpty()) {
                        throw new IllegalStateException("Retrieved retailer ID is null or empty.");
                    }
                    GlobalState.getInstance().setCurrentRetailerId(retailerId);
                    retailerPage.retailerPage();
                }
                else if (customerOptional.isPresent()) {
                    System.out.println("Customer login successful!");
                    String customerId = customerOptional.get().getId();
                    GlobalState.getInstance().setCurrentCustomerId(customerId);
                    //*************Go to Customer Page**************
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
                scanner.close();
                System.exit(0);
                break;

            default:
                System.out.println("\u001B[31mWrong input\u001B[0m");
                performAction();
                break;
        }

    }
//***********SIGN_UP***********************************
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

    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}