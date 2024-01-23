package it.unipi.apparelspotter.apparel.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cloths")
public class ClothMongo {
    @Id
    private String id;

    private String Category;
    private String Type;
    private String Size;
    private String item_name;
    private String Brand;
    private String price;

    @Field("Image url")
    private String imageUrl;

    @Field("Posted Date")
    private Date postedDate;

    private List<Review> Reviews;

    private Retailer retailer;
    private Customer customers;

    //Getter and Setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public List<Review> getReviews() {
        return Reviews;
    }

    public void setReviews(List<Review> reviews) {
        Reviews = reviews;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public Customer getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customers) {
        this.customers = customers;
    }





    public static class Review {
        private int Rating;

        @Id
        private String id;

        public int getRating() {
            return Rating;
        }

        public void setRating(int rating) {
            Rating = rating;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


    public static class Retailer {
        @Id
        private String id;
        private String retailerName;
        private Location Location;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRetailerName() {
            return retailerName;
        }

        public void setRetailerName(String retailerName) {
            this.retailerName = retailerName;
        }

        public ClothMongo.Location getLocation() {
            return Location;
        }

        public void setLocation(ClothMongo.Location location) {
            Location = location;
        }

        // Standard getters and setters
    }


    public static class Customer {
        @Id
        private String id;
        private Location Location;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ClothMongo.Location getLocation() {
            return Location;
        }

        public void setLocation(ClothMongo.Location location) {
            Location = location;
        }


    }


    public static class Location {
        private double Longitude;
        private double Latitude;

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double longitude) {
            Longitude = longitude;
        }

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double latitude) {
            Latitude = latitude;
        }

        // Standard getters and setters
    }
}
