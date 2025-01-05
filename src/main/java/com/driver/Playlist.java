package com.driver;

import java.util.List;

public class Playlist {
    private String title;
    private String mobile;
    private int length;
    private String creator;
    private List<Song> songs;
                    
            public Playlist(){
                    
            }
                    
            public Playlist(String title){
                this.title = title;
            }
            public Playlist(String title, String creator, int length) {
        
                this.title = title;
                    
                this.creator = creator;
                
            this.length = length;
                
        }
                
                
                
        public Playlist(String title, List<Song> songs) {
                
            this.title = title;
                
            this.songs = songs;
            
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
