package it.unipi.apparelspotter.apparel.commands.customer;

import it.unipi.apparelspotter.apparel.Service.AuthService;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.commands.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CustomerPage {
    private final CustomerService customerService;
    private  final BrowseApparelShop browseApparelShop;
    private final AuthService authService;
    private BrowseCloth browseCloth;
    private final NearByApparelShops nearByApparelShops;
    private  final Suggestions suggestions;
    private Auth auth;
    private final Scanner scanner;
    @Autowired
    public CustomerPage(@Lazy Suggestions suggestions, @Lazy BrowseApparelShop browseApparelShop, @Lazy BrowseCloth browseCloth,@Lazy NearByApparelShops nearByApparelShops, AuthService authService, Auth auth, CustomerService customerService) {
        this.authService = authService;
        this.customerService = customerService;
        this.auth=auth;
        this.browseCloth=browseCloth;
        this.nearByApparelShops=nearByApparelShops;
        this.browseApparelShop =browseApparelShop;
        this.suggestions = suggestions;
        this.scanner = new Scanner(System.in);
    }
public void customerpage(){
    System.out.println();
    System.out.println("\u001B[1mCustomer Page\u001B[0m");
    System.out.println("**************************************");
    System.out.println("1. Browse for cloth");
    System.out.println("2. Suggested cloths");
    System.out.println("3. Find cloths by preferences");
    System.out.println("4. Find cloth by cloth name");
    System.out.println("5. Browse apparel");
    System.out.println("6. Nearby apparel shops");
    System.out.println("7. Find Apparel shop by Shop name ");
    System.out.println("8. Popular Retailers");
    System.out.println("9. Popular Brands");
    System.out.println("10. Top like cloths");
    System.out.println("11. view profile");
    System.out.println("12. Edit your profile");
    System.out.println("13. Delete your profile");
    System.out.println("14. log-out");
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
            browseCloth();
            break;
        case 2:
            suggestedCloths();
            break;
        case 3:
            preference();
            break;
        case 4:
            findClothByName();
            break;
        case 5:
            browseApparelShop();
            break;
        case 6:
            nearByApparelShop();
            break;
        case 7:
            findApparelShopByname();
            break;
        case 8:
            popularRetailers();
            break;
        case 9:
            popularBrands();
            break;
        case 10:
            topLikedCloths();
            break;
        case 11:
            EditProfile();
            break;
        case 12:
           DeleteProfile();
            break;
        case 13:
            LogOut();
            break;
        default:
            System.out.println("\u001B[31mWrong input\u001B[0m");
            customerpage();
            break;
    }

}
    public void  browseCloth(){
        System.out.println("\u001B[1mlist of cloths\u001B[0m");
        System.out.println("*************************************************************************************");
        browseCloth.search();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
        }
    public void  suggestedCloths(){
        System.out.println("\u001B[1mlist of Suggested CLoths\u001B[0m");
        System.out.println("**************************************");
        suggestions.suggestedByLikes();
        suggestions.suggestedByFollow();
        suggestions.suggestedByReviewAndRating();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }
    public void  preference(){
        System.out.println("\u001B[1mlist of CLoths based on your preference\u001B[0m");
        System.out.println("**************************************");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }
    public void  findClothByName(){
        System.out.println("\u001B[1msearched cloth\u001B[0m");
        System.out.println("**************************************");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }
    public void  browseApparelShop(){
        System.out.println("\u001B[1mlist of apparel shops\u001B[0m");
        System.out.println("**************************************");
        browseApparelShop.search();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }

    public void  nearByApparelShop(){
        System.out.println("\u001B[1mlist of nearby apparelshops\u001B[0m");
        System.out.println("**************************************");
        nearByApparelShops.NearBy();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }

    public void  findApparelShopByname(){
        System.out.println("\u001B[1m Searched apparel-shop/retailer\u001B[0m");
        System.out.println("**************************************");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }
    public void  popularRetailers(){
        System.out.println("\u001B[1mlist of popular apparele shop/retailers\u001B[0m");
        System.out.println("**************************************");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }
    public void  popularBrands(){
        System.out.println("\u001B[1mlist popular brands\u001B[0m");
        System.out.println("**************************************");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
    }
    public void  topLikedCloths(){
        System.out.println("\u001B[1mlist of top liked cloths\u001B[0m");
        System.out.println("**************************************");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Closing();
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
    public void Closing() {
        System.out.print("Enter Your choice (1 to Back, 2 to leave): ");
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch (input) {
            case 2:
                break;
            case 1:
                customerpage();
                break;
            default:
                System.out.println("\u001B[31mWrong input\u001B[0m");
                Closing();
                break;
        }
    }
}
