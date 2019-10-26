package com.xenakis.model;

public class Chapter {
    private final String id;
    private final String name;
    private final String greekName;

    public Chapter(String id, String name, String greekName) {
        this.id = id;
        this.greekName = greekName;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getGreekName() {
        return this.greekName;
    }
}
