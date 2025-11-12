package com.pluralsight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<Pizza> pizzas = new ArrayList<>();
    private final List<Drink> drinks = new ArrayList<>();
    private final List<GarlicKnots> garlicKnotsItems = new ArrayList<>();
    private final LocalDateTime orderTime;


