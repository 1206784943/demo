package com.example.demo.bridge;

public abstract class Shape {
    DrawAPI drawAPI;
    public Shape() {

    }
    public Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
