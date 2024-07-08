package com.example.demo.bridge;

import org.checkerframework.checker.units.qual.C;

public class Circle extends Shape {
    int x,y,radius;

    public Circle(DrawAPI drawAPI) {
        super(drawAPI);
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }

    public static void main(String[] args) {
        Circle circle = new Circle(new RedCircle());
        circle.drawAPI.drawCircle(1,2,3);
    }
}
