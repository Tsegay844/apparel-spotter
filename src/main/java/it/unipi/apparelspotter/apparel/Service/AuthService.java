package it.unipi.apparelspotter.apparel.Service;

import it.unipi.apparelspotter.apparel.Repository.mongo.CustomerMongoRepository;
import it.unipi.apparelspotter.apparel.Repository.mongo.RetailerMongoRepository;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthService {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final CustomerMongoRepository customerMongoRepository;
    private final RetailerMongoRepository retailerMongoRepository;

    @Autowired
    public AuthService(CustomerMongoRepository customerMongoRepository, RetailerMongoRepository retailerMongoRepository) {
        this.customerMongoRepository = customerMongoRepository;
        this.retailerMongoRepository = retailerMongoRepository;
    }

    public Optional<CustomerMongo> customerLogin(String email, String password) {
        return customerMongoRepository.findByEmailAndPassword(email, password);
    }


    public Optional<RetailerMongo> retailerLogin(String email) {
        Optional<RetailerMongo> retailer = retailerMongoRepository.findByEmail(email);
        return retailer;
    }

    public CustomerMongo customerSignup(String firstName, String lastName, String gender, int contact, String email, String password) {
        Optional<CustomerMongo> existingRetailer = customerMongoRepository.findByEmail(email);
        if (existingRetailer.isPresent()) {

            System.out.println(ANSI_YELLOW + "Email already registered! --> Details not saved" + ANSI_RESET);
            return null;
        }
        else{
        CustomerMongo newCustomer = new CustomerMongo();
        newCustomer.setEmail(email);
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setGender(gender);
        newCustomer.setPhoneNumber(String.valueOf(contact));
        newCustomer.setPassword(password);
        System.out.println("Saving new customer: " + newCustomer.toString());

        CustomerMongo savedCustomer = customerMongoRepository.save(newCustomer);


        System.out.println("Saved customer: " + savedCustomer.toString());
        System.out.println("Customer signup successful!");

        return savedCustomer;}


    }
    public RetailerMongo retailerSignup(String firstName, String lastName, String gender, int contact, String email, String password, String retailerName) {
        Optional<RetailerMongo> existingRetailer = retailerMongoRepository.findByEmail(email);
        if (existingRetailer.isPresent()) {

            System.out.println(ANSI_YELLOW + "Email already registered! --> Details not saved"+ ANSI_RESET);
            return null;
        } else {
            RetailerMongo newRetailer = new RetailerMongo();
            newRetailer.setEmail(email);
            newRetailer.setFirstName(firstName);
            newRetailer.setLastName(lastName);
            newRetailer.setGender(gender);
            newRetailer.setPhoneNumber(String.valueOf(contact));
            newRetailer.setPassword(password);
            newRetailer.setRetailerName(retailerName);

            System.out.println("Saving new retailer: " + newRetailer.toString());

            RetailerMongo savedRetailer = retailerMongoRepository.save(newRetailer);

            System.out.println("Saved retailer: " + savedRetailer.toString());
            System.out.println("Retailer signup successful!");
            return savedRetailer;

        }
    }


}