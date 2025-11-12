package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Pizza implements Pricable {

    private PizzaSize size;
    private CrustType crustType;
    private boolean stuffedCrust;
    private List<Topping> toppings = new ArrayList<>();

