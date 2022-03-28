package com.codegym.service;

import com.codegym.model.Song;
import com.codegym.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongService {
    @Autowired
    SongRepository songRepository;

    public Optional<Song> findById(Long id){
        return songRepository.findById(id);
    }

    public Iterable<Song> findAll(){
        return songRepository.findAll();
    }

    public void save(Song song){
        songRepository.save(song);
    }

}
