package com.example.demo.adapter;

public class AudioPlayer {


    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            Mp3Adapter mediaAdapter = new Mp3Adapter();
            mediaAdapter.play(audioType, fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            Mp4Adapter mp4MediaAdapter = new Mp4Adapter();
            mp4MediaAdapter.playMp4(fileName);
        }else if(audioType.equalsIgnoreCase("vlc")){

        }else{
            System.out.println("Invalid media. "+ audioType + " format not supported");
        }
    }
}
