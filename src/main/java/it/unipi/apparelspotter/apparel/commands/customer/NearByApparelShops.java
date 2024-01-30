package it.unipi.apparelspotter.apparel.commands.customer;
import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;
@Component
public class NearByApparelShops {
    private  final CustomerPage customerPage;
    private final CustomerService customerService;
    private final Scanner scanner;
    @Autowired
    public NearByApparelShops( CustomerPage customerPage,CustomerService customerService){
        this.customerPage = customerPage;
        this.customerService=customerService;
        this.scanner=new Scanner(System.in);
    }
    public void NearBy(){
        String customerId=GlobalState.getInstance().getCurrentCustomerId();
        List<String> nearbyRetailerIds =  customerService.findRetailerIdsNearCustomer(customerId);
        List<RetailerMongo> retailersDetails = customerService.getRetailerDetails(nearbyRetailerIds);

// Now you can iterate over the retailersDetails list and do whatever you need with the data
        for (RetailerMongo retailer : retailersDetails) {
            System.out.println("-------------------------------------------");
            System.out.println("Shope Name: " +retailer.getRetailerName());
            System.out.println("-------------------------------------------");
           System.out.println("Retailer details:");
           System.out.println("    Name: "+retailer.getFirstName() + retailer.getLastName()+ " Gender: "+retailer.getGender());
           System.out.println("    Email: " + retailer.getEmail());
           System.out.println("    Contact: " + retailer.getPhoneNumber());
           System.out.println("    Location: ("+retailer.getLatitude()+ ", "+retailer.getLongitude()+")");
            System.out.println();
           System.out.println();
             //System.out.println(retailer.toString());
        }
    }
    }

