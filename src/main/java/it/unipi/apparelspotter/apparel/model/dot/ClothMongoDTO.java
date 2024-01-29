package it.unipi.apparelspotter.apparel.model.dot;

import org.neo4j.cypherdsl.core.Return;

import java.util.Date;
import java.util.List;


public class ClothMongoDTO {
    private String category;

    // Constructor, getters, and setters
    public ClothMongoDTO(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }




    }
