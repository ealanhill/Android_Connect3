package com.example.ahill.connect3;

/**
 * Created by ahill on 5/4/2016.
 */
public enum Player {
    NONE("None"),
    RED("Red"),
    YELLOW("Yellow");

    private String name;

    Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
