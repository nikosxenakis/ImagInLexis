package com.xenakis.model;

public class Chapter {
    private final int id;
    private final String name;
    private final String greekName;

    public Chapter(int id, String name, String greekName) {
        this.id = id;
        this.greekName = greekName;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getGreekName() {
        return this.greekName;
    }
}
