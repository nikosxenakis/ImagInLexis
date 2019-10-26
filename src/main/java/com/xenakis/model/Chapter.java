package com.xenakis.model;

public class Chapter {
    private final String name;
    private final String greekName;

    public Chapter(String name, String greekName) {
        this.greekName = greekName;
        this.name = name;
    }

    public String getGreekName() {
        return this.greekName;
    }
}
