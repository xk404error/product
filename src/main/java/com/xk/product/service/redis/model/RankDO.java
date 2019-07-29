package com.xk.product.service.redis.model;

public class RankDO {
    private String userId;
    private String rank;
    private String socre;

    public RankDO() {
    }

    public RankDO(String userId, String rank, String socre) {
        this.userId = userId;
        this.rank = rank;
        this.socre = socre;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSocre(String socre) {
        this.socre = socre;
    }

    public String getUserId() {
        return userId;
    }

    public String getRank() {
        return rank;
    }

    public String getSocre() {
        return socre;
    }
}
