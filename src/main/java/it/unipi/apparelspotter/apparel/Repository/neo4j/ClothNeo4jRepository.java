package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.neo4j.ClothNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ClothNeo4jRepository extends Neo4jRepository<ClothNeo4j, String> {

}