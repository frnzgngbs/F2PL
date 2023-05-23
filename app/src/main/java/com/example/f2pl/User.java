package com.example.f2pl;

public class User {
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
    private int hint;
    private int quizzes_taken;
    private boolean science_state, sports_state, math_state, prog_state, gaming_state, history_state;
    private int dailyScore;
    private int position;
    private String position1;
    private String dailyScore1;

    public String getPosition1() {
        return position1;
    }

    public void setPosition1(String position1) {
        this.position1 = position1;
    }

    public String getDailyScore1() {
        return dailyScore1;
    }

    public void setDailyScore1(String dailyScore1) {
        this.dailyScore1 = dailyScore1;
    }

    public User(String position1, String email, String dailyScore1) {
        this.position1 = position1;
        this.email = email;
        this.dailyScore1 = dailyScore1;
    }

    public User(String email, String password, String MPIN, int science_score, int sports_score, int gaming_score, int history_score, int prog_score, int math_score, int coins, int hint,int quizzes_taken, boolean prog_state, boolean science_state, boolean sports_state, boolean history_state, boolean gaming_state, boolean math_state, int dailyScore) {
        this.email = email;
        this.password = password;
        this.MPIN = MPIN;
        this.science_score = science_score;
        this.sports_score = sports_score;
        this.gaming_score = gaming_score;
        this.history_score = history_score;
        this.prog_score = prog_score;
        this.math_score = math_score;
        this.hint = hint;
        this.quizzes_taken = quizzes_taken;
        this.coins = coins;
        this.prog_state = prog_state;
        this.science_state = science_state;
        this.gaming_state = gaming_state;
        this.sports_state = sports_state;
        this.math_state = math_state;
        this.history_state = history_state;
        this.dailyScore = dailyScore;
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

    public int getHint() {
        return hint;
    }

    public void setHint(int hint) {
        this.hint = hint;
    }

    public int getQuizzes_taken() {
        return quizzes_taken;
    }

    public void setQuizzes_taken(int quizzes_taken) {
        this.quizzes_taken = quizzes_taken;
    }

    public boolean isScience_state() {
        return science_state;
    }

    public void setScience_state(boolean science_state) {
        this.science_state = science_state;
    }

    public boolean isSports_state() {
        return sports_state;
    }

    public void setSports_state(boolean sports_state) {
        this.sports_state = sports_state;
    }

    public boolean isMath_state() {
        return math_state;
    }

    public void setMath_state(boolean math_state) {
        this.math_state = math_state;
    }

    public boolean isProg_state() {
        return prog_state;
    }

    public void setProg_state(boolean prog_state) {
        this.prog_state = prog_state;
    }

    public boolean isGaming_state() {
        return gaming_state;
    }

    public void setGaming_state(boolean gaming_state) {
        this.gaming_state = gaming_state;
    }

    public boolean isHistory_state() {
        return history_state;
    }

    public void setHistory_state(boolean history_state) {
        this.history_state = history_state;

    }

    public int getDailyScore() {
        return dailyScore;
    }

    public void setDailyScore(int dailyScore) {
        this.dailyScore = dailyScore;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
