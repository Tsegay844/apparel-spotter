package it.unipi.apparelspotter.apparel.model.dto;

public class TopLikedCloth {
    private String clothId;
        private int likeCount;

        // Constructor, getters and setters
        public TopLikedCloth(String clothId, int likeCount) {
            this.clothId = clothId;
            this.likeCount = likeCount;
        }

        public String getClothId() {
            return clothId;
        }

        public void setClothId(String clothId) {
            this.clothId = clothId;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }
    }

