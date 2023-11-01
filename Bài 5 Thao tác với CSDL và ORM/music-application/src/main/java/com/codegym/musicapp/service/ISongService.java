package com.codegym.musicapp.service;

import com.codegym.musicapp.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();

    void save(Song song);

    Song findById(int id);

    void remove(int id);
}
