package it.unipi.apparelspotter.apparel.Service;
import it.unipi.apparelspotter.apparel.Repository.mongo.ClothMongoRepository;
import it.unipi.apparelspotter.apparel.Repository.neo4j.ClothNeo4jRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrowseService {
    private final ClothMongoRepository clothMongoRepository;
    private final ClothNeo4jRepository clothNeo4jRepository;

    @Autowired
    public BrowseService(ClothMongoRepository clothMongoRepository, ClothNeo4jRepository clothNeo4jRepository) {
        this.clothMongoRepository = clothMongoRepository;
        this.clothNeo4jRepository = clothNeo4jRepository;


}}
