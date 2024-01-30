package it.unipi.apparelspotter.apparel.commands.customer;
import it.unipi.apparelspotter.apparel.GlobalState;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.dto.Preference;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FindByPreference {
    private  final MongoTemplate mongoTemplate;
    private  final CustomerPage customerPage;
    private final CustomerService customerService;
@Autowired
public FindByPreference(CustomerService customerService, CustomerPage customerPage, MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
        this.customerPage=customerPage;
        this.customerService=customerService;
    }
    private List<Preference> temporaryPreferences = new ArrayList<>();
    public void addPreference() {
        System.out.println("Adding a Preference...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a Preference:");
        Preference preference = new Preference();
        System.out.print("Enter category: ");
        preference.setCategory(scanner.nextLine());
        System.out.print("Enter brand: ");
        preference.setBrand(scanner.nextLine());
        System.out.print("Enter size: ");
        preference.setSize(scanner.nextLine());
        temporaryPreferences.add(preference);
        System.out.println("Preference added successfully!");
        showMatchingClothes(preference);
    }

    private void showMatchingClothes(Preference preference) {
        String customeId = GlobalState.getInstance().getCurrentCustomerId();
        if (customeId!=null) {
            Aggregation aggregation = Aggregation.newAggregation(
                    Aggregation.match(Criteria.where("Category").is(preference.getCategory())),
                    Aggregation.match(Criteria.where("Brand").is(preference.getBrand())),
                    Aggregation.match(Criteria.where("Size").is(preference.getSize())),
                    Aggregation.limit(5)
            );

            try {
                List<ClothMongo> matchingClothes = mongoTemplate.aggregate(aggregation, "cloths", ClothMongo.class).getMappedResults();
                System.out.println("Matching Clothes:");
                for (ClothMongo cloth : matchingClothes) {
                    String clothIdString = cloth.getId();
                    ObjectId clothId = new ObjectId(clothIdString);
                    System.out.println();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Item Name: " + cloth.getItem_name());
                    System.out.println("Category: " + cloth.getCategory());
                    System.out.println("Type: " + cloth.getType());
                    System.out.println("Brand: " + cloth.getBrand());
                    System.out.println("Image URL: " + cloth.getImageUrl());
                    System.out.println("Price: " + cloth.getPrice());
                    System.out.println("Number of likes: " + customerService.getNumberOfLikesByClothId(clothId));
                    System.out.println("Number of Reviews: " + customerService.getNumberOfReviewsByClothId(clothId));
                    System.out.println("Average Rating: " + customerService.getAverageRatingByClothId(clothId));
                    System.out.println("Posted By: " + cloth.getRetailer().getRetailerName());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error during aggregation: " + e.getMessage());
            }
        } else {
            System.out.println("No user logged in.");
        }
    }





}
