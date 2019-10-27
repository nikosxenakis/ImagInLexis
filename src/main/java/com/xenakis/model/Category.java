package com.xenakis.model;

public class Category {
    private final int id;
    private final int chapterId;
    private final String greekName;
    private final String name;

    public Category(int id, int chapterId, String name, String greekName) {
        this.id = id;
        this.chapterId = chapterId;
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
