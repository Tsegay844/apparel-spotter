package it.unipi.apparelspotter.apparel.model.dto;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ClothObjectId {

    @Id
    private ObjectId _id;
    private String Category;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public ObjectId getClothId() { // Method renamed to singular since it returns a single ObjectId
        return _id;
    }

    public void setClothId(ObjectId _id) {
        this._id = _id;
    }
}
