package it.unipi.apparelspotter.apparel.commands.customer;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.model.dto.BrandPopularity;
import it.unipi.apparelspotter.apparel.model.dto.TopLikedCloth;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Popularity {
    private  final CustomerPage customerPage;
    private final CustomerService customerService;
    private final Scanner scanner;
    @Autowired
    public Popularity( CustomerPage customerPage,CustomerService customerService){
        this.customerPage = customerPage;
        this.customerService=customerService;
        this.scanner=new Scanner(System.in);
    }
    public void PopularBrands(){
            List<BrandPopularity> popularBrands = customerService.getPopularBrands();
            popularBrands.forEach(brand -> {
                System.out.println();
                System.out.println("--------------------------------------");
                System.out.println("Brand: " + brand.getBrand());
                System.out.println("Average Rating: " + brand.getAverageRating());
                System.out.println("Review Count: " + brand.getReviewCount());

            });
        }
    public void TopLIkeCloths() {
        List<TopLikedCloth> topCloths = customerService.getTopLikedCloths();
        topCloths.forEach(cloth -> ClothProfile(cloth.getClothId())); }
    public void ClothProfile(String clothIdStr){
        ObjectId clothId = new ObjectId(clothIdStr);
        Optional<ClothMongo> clothOptional = customerService.findClothById(clothId);
        System.out.println();
        System.out.println("Number of likes: " + customerService.getNumberOfLikesByClothId(clothId));
        clothOptional.ifPresent(clothDetails -> System.out.println("Cloth Name: " + clothDetails.getItem_name()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Category: " + clothDetails.getCategory()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Type: " + clothDetails.getType()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Brand: " + clothDetails.getBrand()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Size: " + clothDetails.getSize()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Image URL: " + clothDetails.getImageUrl()));
        clothOptional.ifPresent(clothDetails -> System.out.println("Price: " + clothDetails.getPrice()));
        System.out.println("Number of Reviews: " + customerService.getNumberOfReviewsByClothId(clothId));
        System.out.printf("Average Rating: %.2f%n", customerService.getAverageRatingByClothId(clothId));
        clothOptional.ifPresent(clothDetails -> System.out.println("Posted By: " + clothDetails.getRetailer().getRetailerName()));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }


    }








