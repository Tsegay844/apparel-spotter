package it.unipi.apparelspotter.apparel.commands.retailer;

import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Service.AuthService;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.commands.Auth;
import it.unipi.apparelspotter.apparel.commands.customer.CustomerPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class RetailerPage {
    private final CustomerService customerService;
    private final AuthService authService;
    private final Postcloth postcloth;
    private final Statistics statistics;
    private final ClothList clothlist;
    private final Scanner scanner;
    private final ProfileRtailer profileRtailer;
    private Auth auth;

    @Autowired
    public RetailerPage(@Lazy ProfileRtailer profileRtailer, @Lazy Statistics statistics, @Lazy ClothList clothlist, AuthService authService, @Lazy Auth auth, Postcloth postcloth, CustomerPage customerPage, CustomerService customerService) {
        this.authService = authService;
        this.statistics=statistics;
        this.clothlist=clothlist;
        this.customerService = customerService;
        this.postcloth=postcloth;
        this.profileRtailer = profileRtailer;
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
        System.out.println("4. View your profileRtailer");
        System.out.println("5. Edit your profileRtailer");
        System.out.println("6. Delete your profileRtailer");
        System.out.println("7. Log-out");
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
                ViewProfile();
                break;
            case 5:
                EditProfile();
                break;
            case 6:
                DeleteProfile();
                break;
            case 7:
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
        postcloth.postCloth();
        Closing();
    }
    public void  statistics(){
        System.out.println("\u001B[1mStatistics\u001B[0m");
        System.out.println("**************************************");
        statistics.numberOfCloths();
        System.out.println();
        statistics.printClothsCountByCategory();
        statistics.averageRating();
        statistics.totalFollowersByRetailer();
        statistics.totalLikesOfClothsByRetailer();
        statistics.topLikedClothsOfRetailer();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }
    public void  listOfYourCloths(){
        System.out.println("\u001B[1mList Of Cloths\u001B[0m");
        System.out.println("**************************************");
        clothlist.printRandomClothsByRetailer();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }
    public void  ViewProfile(){
        System.out.println("\u001B[1mEdit your profileRtailer\u001B[0m");
        profileRtailer.viewProfile();
        System.out.println("**************************************");
        Closing();
    }
    public void  EditProfile(){
        System.out.println("\u001B[1mEdit your profileRtailer\u001B[0m");
        profileRtailer.editProfile();
        System.out.println("**************************************");
        Closing();
    }
    public void  DeleteProfile(){
        System.out.println("\u001B[1mYou are deleting your profileRtailer\u001B[0m");
        deleteProfile();
        System.out.println("**************************************");
        auth.performAction();
    }
    public void  LogOut(){
        auth.performAction();

    }
    public void Closing(){
        System.out.print("Enter Your choice (1 to continue, 2 to back): ");
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch (input){
            case 1:
                break;
            case 2:
                retailerPage();
                break;
            default:
                System.out.println("\u001B[31mWrong input\u001B[0m");
                Closing();
                break;
        }
    }
    public void deleteProfile() {
        System.out.println("Please enter your email to confirm account deletion:");
        String email = scanner.nextLine();

        boolean isDeleted = customerService.deleteRetailerAccountByEmail(email);
        if (isDeleted) {
            System.out.println("Your account successfully deleted.");
            String id = GlobalState.getInstance().getCurrentRetailerId();
            customerService.deleteRetailerNode(id);
            System.out.println("Retailer node successfully deleted.");

        } else {
            System.out.println("No account found with that email, or deletion failed.");
        }


    }
    }
