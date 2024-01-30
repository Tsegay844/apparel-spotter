package it.unipi.apparelspotter.apparel.model.dto;

public class Preference {
        private String Category;
        private String Brand;
        private String Size;

    public Preference() {

    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public Preference(String category, String brand, String size) {
        Category = category;
        Brand = brand;
        Size = size;
    }

}
