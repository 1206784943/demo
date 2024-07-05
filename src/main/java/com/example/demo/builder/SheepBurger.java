package com.example.demo.builder;

public class SheepBurger extends Burger {
    @Override
    public String name() {
        return "羊肉汉堡";
    }

    @Override
    public Float price() {
        return 3f;
    }
}
