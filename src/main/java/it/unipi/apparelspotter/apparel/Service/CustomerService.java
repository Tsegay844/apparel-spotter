package it.unipi.apparelspotter.apparel.Service;

import it.unipi.apparelspotter.apparel.Repository.mongo.ClothMongoRepository;
import it.unipi.apparelspotter.apparel.Repository.mongo.CustomerMongoRepository;
import it.unipi.apparelspotter.apparel.Repository.mongo.RetailerMongoRepository;
import it.unipi.apparelspotter.apparel.Repository.neo4j.ClothNeo4jRepository;
import it.unipi.apparelspotter.apparel.Repository.neo4j.RetailerNeo4jRepository;
import it.unipi.apparelspotter.apparel.model.dto.*;
import it.unipi.apparelspotter.apparel.model.mongo.ClothMongo;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerMongo;
import it.unipi.apparelspotter.apparel.model.neo4j.ClothNeo4j;
import it.unipi.apparelspotter.apparel.model.neo4j.CustomerNeo4j;
import it.unipi.apparelspotter.apparel.Repository.neo4j.CustomerNeo4jRepository;
import it.unipi.apparelspotter.apparel.model.neo4j.RetailerNeo4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerMongoRepository customerMongoRepository;
    private final ClothMongoRepository clothMongoRepository;
    private final ClothNeo4jRepository clothNeo4jRepository;
    private final CustomerNeo4jRepository customerNeo4jRepository;
    private final RetailerNeo4jRepository retailerNeo4jRepository;
    private final RetailerMongoRepository retailerMongoRepository;


    @Autowired
    public CustomerService(RetailerMongoRepository retailerMongoRepository, RetailerNeo4jRepository retailerNeo4jRepository, ClothNeo4jRepository clothNeo4jRepository, ClothMongoRepository clothMongoRepository, CustomerMongoRepository customerMongoRepository, CustomerNeo4jRepository customerNeo4jRepository) {
        this.customerMongoRepository = customerMongoRepository;
        this.customerNeo4jRepository = customerNeo4jRepository;
        this.clothMongoRepository = clothMongoRepository;
        this.clothNeo4jRepository = clothNeo4jRepository;
        this.retailerNeo4jRepository = retailerNeo4jRepository;
        this.retailerMongoRepository = retailerMongoRepository;
    }

    // MongoDB CRUD operations
    public List<CustomerMongo> getAllMongoCustomers() {
        return customerMongoRepository.findAll();
    }

    public Optional<CustomerMongo> getMongoCustomerById(String id) {
        return customerMongoRepository.findById(id);
    }

    public CustomerMongo saveMongoCustomer(CustomerMongo customer) {
        return customerMongoRepository.save(customer);
    }

    public void deleteMongoCustomer(String id) {
        customerMongoRepository.deleteById(id);
    }

    // Neo4j CRUD operations
    public List<CustomerNeo4j> getAllNeo4jCustomers() {
        return customerNeo4jRepository.findAllCustomers();
    }

    /*  public Optional<CustomerNeo4j> getNeo4jCustomerById(String _id) {
          return Optional.ofNullable(customerNeo4jRepository.findCustomerByMongoId(_id));
      }*/
    public Optional<CustomerNeo4j> getNeo4jCustomerById(String _id) {
        return customerNeo4jRepository.findCustomerByMongoId(_id);
    }

    public CustomerNeo4j saveNeo4jCustomer(CustomerNeo4j customer) {
        return customerNeo4jRepository.save(customer);
    }

    public void deleteNeo4jCustomer(String id) {
        customerNeo4jRepository.deleteById(id);
    }

    public ClothMongo getRandomCloth() {
        return clothMongoRepository.findRandomCloth()
                .orElseThrow(() -> new NoSuchElementException("No cloth found in the database."));
    }

    public Double getAverageRatingByClothId(ObjectId clothId) {
        return clothMongoRepository.getAverageRatingByClothId(clothId);
    }

    public Integer getNumberOfReviewsByClothId(ObjectId clothId) {
        return clothMongoRepository.getNumberOfReviewsByClothId(clothId);
    }

    public Integer getNumberOfLikesByClothId(ObjectId clothId) {
        String idAsString = clothId.toString();
        return clothNeo4jRepository.getNumberOfLikesByClothId(idAsString);
    }

    public Integer getNumberOfFollowersByClothId(String retailerId) {
        return retailerNeo4jRepository.countTotalFollowersByRetailer(retailerId);
    }

    @Transactional
    public void followRetailer(String customerId, String retailerMongoId) {
        CustomerNeo4j customer = customerNeo4jRepository.findCustomerByMongoId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        RetailerNeo4j retailer = retailerNeo4jRepository.findRetailerByMongoId(retailerMongoId)
                .orElseThrow(() -> new RuntimeException("Cloth not found"));
        customer.getFollowedRetailers().add(retailer);
        customerNeo4jRepository.save(customer);
    }

    @Transactional
    public void likeCloth(String customerId, String clothMongoId) {
        CustomerNeo4j customer = customerNeo4jRepository.findCustomerByMongoId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        ClothNeo4j cloth = clothNeo4jRepository.findClothByMongoId(clothMongoId)
                .orElseThrow(() -> new RuntimeException("Cloth not found"));

        customer.getLikedCloths().add(cloth);
        customerNeo4jRepository.save(customer);
    }

    public Optional<ClothMongo> findClothById(ObjectId _id) {

        return clothMongoRepository.findById(_id);
    }
    public Optional<RetailerMongo> findRetailerById(ObjectId _id) {
        return retailerMongoRepository.findRetailerById(_id);
    }
    public List<String> findRetailerIdsNearCustomer(String customerId) {
        return retailerNeo4jRepository.findNearbyRetailersByCustomerId(customerId);
    }
    public List<RetailerMongo> getRetailerDetails(List<String> retailerIds) {
        return retailerMongoRepository.findByIdIn(retailerIds);
    }
    public RetailerMongo getRandomApparelShop() {
        return retailerMongoRepository.findRandomApparel()
                .orElseThrow(() -> new NoSuchElementException("No cloth found in the database."));
    }

    public ReviewState getRetailerStats(String retailerIdStr) {
        ObjectId retailerId = new ObjectId(retailerIdStr);
        return clothMongoRepository.getAverageRatingAndReviewRetailer(retailerId)
                .orElseThrow(() -> new RuntimeException("Retailer not found or no reviews"));
    }

    public List<String> findTopLikedClothsByCustomerId(String customerId) {
        return customerNeo4jRepository.findTopLikedClothsByCustomerId(customerId);
    }
    public List<String> findTopClothsForTopRetailers(String customerId) {
        return customerNeo4jRepository.findTopClothsForTopRetailers(customerId);
    }

    public List<ClothIds> findTopRatedAndReviewClothes() {
        List<ClothIds> results = clothMongoRepository.findTopRatedClothes();
        if (results == null || results.isEmpty()) {
            // Log an error or throw an exception
            System.out.println("No results found or there was an error executing the aggregation.");
        }
        return results;
    }
    public List<ClothFollowLike> TopLikedClothesByFollowers(String customerId) {
        return clothNeo4jRepository.findTopLikedClothesByFollowers(customerId);
    }

    public boolean deleteCustomerAccountByEmail(String email) {
        Long deletedCount = customerMongoRepository.deleteByEmail(email);
        return deletedCount > 0;
    }

    @Transactional
    public void deleteCustomerNode(String propertyId) {
        Optional<CustomerNeo4j> customer = customerNeo4jRepository.findById(propertyId);
        if (customer.isPresent()) {
            customerNeo4jRepository.deleteCustomerByPropertyId(propertyId);

        } else {
            System.out.println("node not deleted!");
        }
    }
    public boolean deleteRetailerAccountByEmail(String email) {
        Long deletedCount = retailerMongoRepository.deleteByEmail(email);
        return deletedCount > 0;
    }

    @Transactional
    public void deleteRetailerNode(String propertyId) {
        Optional<RetailerNeo4j> retailer = retailerNeo4jRepository.findById(propertyId);
        if (retailer.isPresent()) {
            customerNeo4jRepository.deleteCustomerByPropertyId(propertyId);
        } else {
            System.out.println("node not deleted!");
        }
    }

    public List<BrandPopularity> getPopularBrands() {
        return clothMongoRepository.findPopularBrands();
    }
    public List<TopLikedCloth> getTopLikedCloths() {
        return clothNeo4jRepository.findTop5LikedCloths();
    }
    }
