package it.unipi.apparelspotter.apparel.Repository.neo4j;

import it.unipi.apparelspotter.apparel.model.neo4j.RetailerNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RetailerNeo4jRepository extends Neo4jRepository<RetailerNeo4j, String> {
}
