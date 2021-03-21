package ru.itis.springdemo.models;

public enum State {
    NOT_CONFIRMED("NOT_CONFIRMED"), CONFIRMED("CONFIRMED");
    private String state;
    State(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
