package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {
    public HashMap<Artist, List<Album>> artistAlbumMap;
    public HashMap<Album, List<Song>> albumSongMap;
    public HashMap<Playlist, List<Song>> playlistSongMap;
    public HashMap<Playlist, List<User>> playlistListenerMap;
    public HashMap<User, Playlist> creatorPlaylistMap;
    public HashMap<User, List<Playlist>> userPlaylistMap;
    public HashMap<Song, List<User>> songLikeMap;

    public List<User> users;
    public List<Song> songs;
    public List<Playlist> playlists;
    public List<Album> albums;
    public List<Artist> artists;

    public SpotifyRepository(){
        //To avoid hitting apis multiple times, initialize all the hashmaps here with some dummy data
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();

        users = new ArrayList<>();
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
    }

    public User createUser(String name, String mobile) {
        User user = new User(name,mobile);
        return user;
    }

    public Artist createArtist(String name) {
        Artist artist = new Artist(name);
        return artist;
    }

    public Album createAlbum(String title, String artistName) {
        Artist artist = null;
        for(Artist a:artists){
            if(a.getName().equals(artistName)){
                artist = a;
                break;
            }
        }
        if(artist == null){
            artist = createArtist(artistName);
        }
        Album album = new Album(title,artist);
        albums.add(album);
        artistAlbumMap.computeIfAbsent(artist, k -> new ArrayList<>()).add(album);
        return album;
    }

    public Song createSong(String title, String albumName, int length) throws Exception{
        Album album = null;
        for (Album a : albums) {
            if (a.getTitle().equals(albumName)) {
                album = a;
                break;
            }
        }
        if (album == null) {
            throw new Exception("Album not found");
        }
        Song song = new Song(title, album, length);
        songs.add(song);
        albumSongMap.computeIfAbsent(album, k -> new ArrayList<>()).add(song);
        return song;
    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
        User user = null;
        for (User u : users) {
            if (u.getMobile().equals(mobile)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            throw new Exception("User not found");
        }
    
        List<Song> matchingSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getLength() == length) {
                matchingSongs.add(song);
            }
        }
    
        Playlist playlist = new Playlist(title, matchingSongs);
        playlists.add(playlist);
        playlistListenerMap.computeIfAbsent(playlist, k -> new ArrayList<>()).add(user);
        userPlaylistMap.computeIfAbsent(user, k -> new ArrayList<>()).add(playlist);
        creatorPlaylistMap.put(user, playlist);
    
        return playlist;
    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {
        User user = null;
    for (User u : users) {
        if (u.getMobile().equals(mobile)) {
            user = u;
            break;
        }
    }
    if (user == null) {
        throw new Exception("User not found");
    }

    List<Song> matchingSongs = new ArrayList<>();
    for (String songTitle : songTitles) {
        for (Song song : songs) {
            if (song.getTitle().equals(songTitle)) {
                matchingSongs.add(song);
                break;
            }
        }
    }

    Playlist playlist = new Playlist(title, matchingSongs);
    playlists.add(playlist);
    playlistListenerMap.computeIfAbsent(playlist, k -> new ArrayList<>()).add(user);
    userPlaylistMap.computeIfAbsent(user, k -> new ArrayList<>()).add(playlist);
    creatorPlaylistMap.put(user, playlist);

    return playlist;
    }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {
        User user = null;
    for (User u : users) {
        if (u.getMobile().equals(mobile)) {
            user = u;
            break;
        }
    }
    if (user == null) {
        throw new Exception("User not found");
    }

    for (Playlist playlist : playlists) {
        if (playlist.getTitle().equals(playlistTitle)) {
            return playlist;
        }
    }

    throw new Exception("Playlist not found");
    }

    public Song likeSong(String mobile, String songTitle) throws Exception {
        User user = null;
    for (User u : users) {
        if (u.getMobile().equals(mobile)) {
            user = u;
            break;
        }
    }
    if (user == null) {
        throw new Exception("User not found");
    }

    Song song = null;
    for (Song s : songs) {
        if (s.getTitle().equals(songTitle)) {
            song = s;
            break;
        }
    }
    if (song == null) {
        throw new Exception("Song not found");
    }

    songLikeMap.computeIfAbsent(song, k -> new ArrayList<>()).add(user);
    return song;
    }

    public String mostPopularArtist() {
        Map<Artist, Integer> artistLikes = new HashMap<>();

    for (Map.Entry<Song, List<User>> entry : songLikeMap.entrySet()) {
        Song song = entry.getKey();
        int likes = entry.getValue().size();
        Artist artist = (Artist)song.getAlbum();

        artistLikes.put(artist, artistLikes.getOrDefault(artist, 0) + likes);
    }

    Artist mostPopularArtist = null;
    int maxLikes = 0;

    for (Map.Entry<Artist, Integer> entry : artistLikes.entrySet()) {
        if (entry.getValue() > maxLikes) {
            maxLikes = entry.getValue();
            mostPopularArtist = entry.getKey();
        }
    }

    return mostPopularArtist != null ? mostPopularArtist.getName() : null;
    }

    public String mostPopularSong() {
        Song mostPopularSong = null;
    int maxLikes = 0;

    for (Map.Entry<Song, List<User>> entry : songLikeMap.entrySet()) {
        int likes = entry.getValue().size();
        if (likes > maxLikes) {
            maxLikes = likes;
            mostPopularSong = entry.getKey();
        }
    }

    return mostPopularSong != null ? mostPopularSong.getTitle() : null;
    }
}
