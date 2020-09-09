package com.twu.pojo;

public class User {
    private String name;

    private String password;

    private Integer userType;

    private final Integer DEFAULT_VOTES = 10;

    private Integer remainVotes;

    public User(String name, String password, Integer userType) {
        this.remainVotes = DEFAULT_VOTES;
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer type) {
        this.userType = type;
    }


    public Integer getRemainVotes() {
        return remainVotes;
    }

    public void setRemainVotes(Integer remainVotes) {
        this.remainVotes = remainVotes;
    }
}
