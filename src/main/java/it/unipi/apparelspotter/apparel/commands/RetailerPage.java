package it.unipi.apparelspotter.apparel.commands;

import it.unipi.apparelspotter.apparel.Service.AuthService;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class RetailerPage {
    private final CustomerService customerService;
    private final AuthService authService;
    private final Scanner scanner;
    private Auth auth;

    @Autowired
    public RetailerPage(AuthService authService, @Lazy Auth auth, CustomerPage customerPage, CustomerService customerService) {
        this.authService = authService;
        this.customerService = customerService;
        this.auth=auth;
        this.scanner = new Scanner(System.in);
    }
    public void retailerPage(){
        System.out.println();
        System.out.println("\u001B[1mRetailer Page\u001B[0m");
        System.out.println("**************************************");
        System.out.println("1. Post cloth");
        System.out.println("2. View your statistics");
        System.out.println("3. View your list of cloths");
        System.out.println("4. Edit your profile");
        System.out.println("5. Delete your profile");
        System.out.println("6. Log-out");
        System.out.print("Enter your choice (0 to exit): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch (choice){
            case 0:
                System.out.println("Exiting the application...");
                scanner.close();
                System.exit(0);
                break;
            case 1:
                postCloth();
                break;
            case 2:
                statistics();
                break;
            case 3:
                listOfYourCloths();
                break;
            case 4:
                EditProfile();
                break;
            case 5:
                DeleteProfile();
                break;
            case 6:
                LogOut();
                break;
            default:
                System.out.println("\u001B[31mWrong input\u001B[0m");
                retailerPage();
                break;
        }

    }
    public void  postCloth(){
        System.out.println("\u001B[1mPosting page\u001B[0m");
        System.out.println("**************************************");
    }
    public void  statistics(){
        System.out.println("\u001B[1mStatistics\u001B[0m");
        System.out.println("**************************************");
    }
    public void  listOfYourCloths(){
        System.out.println("\u001B[1mStatistics\u001B[0m");
        System.out.println("**************************************");
    }


    public void  EditProfile(){
        System.out.println("\u001B[1mEdit your profile\u001B[0m");
        System.out.println("**************************************");
    }
    public void  DeleteProfile(){
        System.out.println("\u001B[1mYou are deleting your profile\u001B[0m");
        System.out.println("**************************************");
    }

    public void  LogOut(){
        auth.performAction();

    }



}
