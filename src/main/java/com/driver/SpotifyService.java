package com.driver;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SpotifyService {

    //Auto-wire will not work in this case, no need to change this and add autowire

    SpotifyRepository spotifyRepository = new SpotifyRepository();

    public User createUser(String name, String mobile){
        SpotifyRepository spotifyRepository = new SpotifyRepository();
        return spotifyRepository.createUser(name, mobile);
    }

    public Artist createArtist(String name) {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.createArtist(name);
    }

    public Album createAlbum(String title, String artistName) {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.createAlbum(title, artistName);
    }

    public Song createSong(String title, String albumName, int length) throws Exception {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.createSong(title, albumName, length);
    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.createPlaylistOnLength(mobile, title, length);
    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.createPlaylistOnName(mobile, title, songTitles);
    }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.findPlaylist(mobile, playlistTitle);
    }

    public Song likeSong(String mobile, String songTitle) throws Exception {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.likeSong(mobile, songTitle);
    }

    public String mostPopularArtist() {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.mostPopularArtist();
    }

    public String mostPopularSong() {
        SpotifyRepository repository = new SpotifyRepository();
        return repository.mostPopularSong();
    }
}
