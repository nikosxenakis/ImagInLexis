package com.xenakis.model;

public class Category {
    private final String chapterId;
    private final String greekName;
    private final String name;

    public Category(String chapterId, String greekName, String name) {
        this.chapterId = chapterId;
        this.greekName = greekName;
        this.name = name;
    }

    public String getGreekName() {
        return this.greekName;
    }
}
