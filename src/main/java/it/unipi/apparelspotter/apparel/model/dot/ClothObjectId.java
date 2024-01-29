package it.unipi.apparelspotter.apparel.model.dot;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ClothObjectId {

    @Id
    private ObjectId _id;
    private String Category;// Use of @Id annotation to indicate this field is the identifier in the MongoDB collection

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
