package com.example.demo.bridge;

import org.checkerframework.checker.units.qual.C;

public class Circle extends Shape {
    int x,y,radius;

    public Circle(int x,int y,int radius,DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }

    public static void main(String[] args) {
        Shape circle = new Circle(1, 2, 3, new RedCircle());
        circle.draw();
    }
}
