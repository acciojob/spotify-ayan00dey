package com.driver;

public class Song {
    private String title;
    private int length;
    private int likes;
    private Album album;
    
        public Song(){
    
        }
    
        public Song(String title, int length){
            this.title = title;
            this.length = length;
        }
        public Song(String title, Album album, int length) {
    
            this.title = title;
    
            this.album = album;

        this.length = length;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Object getAlbum() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAlbum'");
    }
}
