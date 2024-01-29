package it.unipi.apparelspotter.apparel.commands.retailer;

import it.unipi.apparelspotter.apparel.Service.RetailerService;
import it.unipi.apparelspotter.apparel.commands.customer.CustomerPage;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Scanner;

@Component
public class Postcloth {
    private final CustomerPage customerPage;
    private  final RetailerService retailerService;
    private final Scanner scanner;
    public Postcloth(CustomerPage customerPage, RetailerService retailerService){
        this.customerPage=customerPage;
        this.retailerService = retailerService;
        this.scanner=new Scanner(System.in);
    }
    public void postCloth(){
            System.out.print("Category: ");
            String category = scanner.nextLine();
            System.out.print("Type: ");
            String type = scanner.nextLine();
            System.out.print("Brand: ");
            String brand = scanner.nextLine();
            System.out.print("Cloth name: ");
            String clothName = scanner.nextLine();
            System.out.print("Size: ");
            String size = scanner.nextLine();
            System.out.print("Price: ");
            String price = scanner.nextLine();
            System.out.print("Image Url: ");
            String imageUrl = scanner.nextLine();
            System.out.print("posted date ");
            String postedDate = scanner.nextLine();

            retailerService.postCloth(category, type, size, brand, price, imageUrl, postedDate);
        }
    }

