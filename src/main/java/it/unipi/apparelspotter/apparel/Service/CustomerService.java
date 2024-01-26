package it.unipi.apparelspotter.apparel.Service;

import it.unipi.apparelspotter.apparel.Repository.mongo.CustomerMongoRepository;
import it.unipi.apparelspotter.apparel.model.mongo.CustomerMongo;
import it.unipi.apparelspotter.apparel.model.neo4j.CustomerNeo4j;
import it.unipi.apparelspotter.apparel.Repository.neo4j.CustomerNeo4jRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerMongoRepository customerMongoRepository;
    private final CustomerNeo4jRepository customerNeo4jRepository;

    @Autowired
    public CustomerService(CustomerMongoRepository customerMongoRepository, CustomerNeo4jRepository customerNeo4jRepository) {
        this.customerMongoRepository = customerMongoRepository;
        this.customerNeo4jRepository = customerNeo4jRepository;
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
}