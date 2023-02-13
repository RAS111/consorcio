package com.c823.consorcio.enums;

public enum Turn {

    MORNING("MORNING"), AFTERNOON("AFTERNOON"), NIGHT("NIGHT");

    private final String name ;

    Turn (String name) {
        this.name = name;
    }
    public Turn getName() {
        return Turn.valueOf(name);
    }

}
