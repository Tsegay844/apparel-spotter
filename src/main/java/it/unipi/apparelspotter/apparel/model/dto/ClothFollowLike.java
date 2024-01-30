package it.unipi.apparelspotter.apparel.model.dto;

public class ClothFollowLike {
    private final String id;
    private final int likes;

    public ClothFollowLike(String id, int likes) {
        this.id = id;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }
}