package com.pluralsight;

public class Topping {

    private final String name;
    private final ToppingType type;
    private final boolean extra; // true = extra meat/cheese portion

    public Topping(String name, ToppingType type) {
        this(name, type, false);
    }

    public Topping(String name, ToppingType type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return extra;
    }


