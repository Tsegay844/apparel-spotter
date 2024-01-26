package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.neo4j.ClothNeo4j;
import it.unipi.apparelspotter.apparel.model.neo4j.RetailerNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface ClothNeo4jRepository extends Neo4jRepository<ClothNeo4j, String> {

}
