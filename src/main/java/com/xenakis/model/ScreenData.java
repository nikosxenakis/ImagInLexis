package com.xenakis.model;

public class ScreenData {
    int id;
    int screenTypeId;
    String name;
    String path;

    public ScreenData(int id, int screenTypeId, String name, String path) {
        this.id = id;
        this.screenTypeId = screenTypeId;
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }
}
