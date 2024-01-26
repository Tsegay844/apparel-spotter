package it.unipi.apparelspotter.apparel.Repository.mongo;
import it.unipi.apparelspotter.apparel.model.mongo.RetailerAverageRating;

import java.util.List;

public interface ClothMongoRepositoryCustom {
    List<RetailerAverageRating> getAverageRatingForEachRetailer();
}
