package com.example.demo.builder;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private List<Item> list = new ArrayList<>();

    public void addItem(Item item) {
        list.add(item);
    }

    public void showItems(){
        float f = 0.0f;
        for (Item item : list) {
            f += item.price();
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
        System.out.println("SumPrice :" + f);
    }

    //建造者模式
    public static void main(String[] args) {
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder.addItem(new ChickenBurger());
        itemBuilder.addItem(new SheepBurger());
        itemBuilder.showItems();
    }
}
