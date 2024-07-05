package com.example.demo.builder;

public class ChickenBurger implements Item {

    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public Float price() {
        return 2f;
    }

    @Override
    public Packing packing() {
        return new Plastic();
    }
}
