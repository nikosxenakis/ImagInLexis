package com.xenakis.model;

public class Category {
    private String chapterId;
    private String greekName;
    private String name;

    public Category(String chapterId, String greekName, String name) {
        this.chapterId = chapterId;
        this.greekName = greekName;
        this.name = name;
    }

    public String getGreekName() {
        return this.greekName;
    }
}
