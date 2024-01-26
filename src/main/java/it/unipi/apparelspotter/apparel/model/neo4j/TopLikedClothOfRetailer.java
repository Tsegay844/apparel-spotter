package it.unipi.apparelspotter.apparel.model.neo4j;

public class TopLikedClothOfRetailer {
        private String clothId;
        private int likes;

    public TopLikedClothOfRetailer(String clothId, int likes) {
        this.clothId = clothId;
        this.likes = likes;
    }

    public String getClothId() {
        return clothId;
    }

    public void setClothId(String clothId) {
        this.clothId = clothId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
