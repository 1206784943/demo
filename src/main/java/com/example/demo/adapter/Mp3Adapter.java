package com.example.demo.adapter;

public class Mp3Adapter implements MediaPlayer{
    @Override
    public void play(String audioType, String fileName) {
        System.out.println("Playing mp3 file. Name: "+ fileName);
    }
}
