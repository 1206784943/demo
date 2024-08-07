package com.example.demo.proxy;

public class ProxyImage implements Image{
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }

    public static void main(String[] args) {
        ProxyImage proxyImage = new ProxyImage("C:\\");
        proxyImage.display();
        proxyImage.display();
    }
}
