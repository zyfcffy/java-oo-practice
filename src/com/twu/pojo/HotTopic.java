package com.twu.pojo;

public class HotTopic {
    private String hotTopicName;

    private Integer rank;

    private Integer votes;

    private Boolean isSuperHot;

    private Integer price;

    private Boolean isPurchased;

    private Boolean isDeleted;

    public HotTopic(String hotTopicName , Boolean isSuperHot){
        this.hotTopicName = hotTopicName;
        this.isSuperHot = isSuperHot;
        this.rank = 0;
        this.votes = 0;
        this.price = 0;
        this.isPurchased = false;
        this.isDeleted = false;
    }

    public HotTopic() {

    }

    public String getHotTopicName() {
        return hotTopicName;
    }

    public void setHotTopicName(String hotTopicName) {
        this.hotTopicName = hotTopicName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Boolean getSuperHot() {
        return isSuperHot;
    }

    public void setSuperHot(Boolean superHot) {
        isSuperHot = superHot;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getPurchased() {
        return isPurchased;
    }

    public void setPurchased(Boolean purchased) {
        isPurchased = purchased;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "HotTopic{" +
                "hotTopicName='" + hotTopicName + '\'' +
                ", rank=" + rank +
                ", votes=" + votes +
                ", isSuperHot=" + isSuperHot +
                ", price=" + price +
                ", isPurchased=" + isPurchased +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
