package it.unipi.apparelspotter.apparel.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "retailers")

@Getter // Generates all the getters
@Setter // Generates all the setters
// Generates an all-args constructor
@EqualsAndHashCode // Generates equals and hashCode methods
@ToString // Generates toString method
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailerMongo {
    @Id
    public String id;
    @Field("retailerName")
    public String retailerName;
    @Field("first_name")
    public String firstName;
    @Field("last_name")
    public String lastName;
    public String gender;
    public String email;
    @Field("Contact number")
    public String phoneNumber;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String Password;
    @Field("Longitude")
    public double longitude;
    @Field("Latitude")
    public double latitude;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
