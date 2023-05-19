package com.example.f2pl;

public class User {
    private String uid;
    private String email;
    private String password;
    private String MPIN;
    private int science_score;
    private int sports_score;
    private int gaming_score;
    private int history_score;
    private int prog_score;
    private int math_score;
    private int coins;

    public User(String uid, String email, String password, String MPIN, int science_score, int sports_score, int gaming_score, int history_score, int prog_score, int math_score, int coins) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.MPIN = MPIN;
        this.science_score = science_score;
        this.sports_score = sports_score;
        this.gaming_score = gaming_score;
        this.history_score = history_score;
        this.prog_score = prog_score;
        this.math_score = math_score;
        this.coins = coins;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getMPIN() {
        return MPIN;
    }

    public void setMPIN(String MPIN) {
        this.MPIN = MPIN;
    }

    public int getScience_score() {
        return science_score;
    }

    public void setScience_score(int science_score) {
        this.science_score = science_score;
    }

    public int getSports_score() {
        return sports_score;
    }

    public void setSports_score(int sports_score) {
        this.sports_score = sports_score;
    }

    public int getGaming_score() {
        return gaming_score;
    }

    public void setGaming_score(int gaming_score) {
        this.gaming_score = gaming_score;
    }

    public int getHistory_score() {
        return history_score;
    }

    public void setHistory_score(int history_score) {
        this.history_score = history_score;
    }

    public int getProg_score() {
        return prog_score;
    }

    public void setProg_score(int prog_score) {
        this.prog_score = prog_score;
    }

    public int getMath_score() {
        return math_score;
    }

    public void setMath_score(int math_score) {
        this.math_score = math_score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
