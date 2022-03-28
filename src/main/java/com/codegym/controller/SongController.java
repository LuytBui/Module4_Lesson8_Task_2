package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    SongService songService;

    @GetMapping("/list")
    public ModelAndView showList(){
        Iterable<Song> songs = songService.findAll();
        return new ModelAndView("list", "songs", songs);
    }

    @GetMapping("/add")
    public ModelAndView showCreateForm(){
        return new ModelAndView("create", "song", new Song());
    }

    @PostMapping("/add")
    public String createSong(@Validated @ModelAttribute Song song, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors())
            return "create";
        songService.save(song);
        return "redirect:/songs/list";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Song> songOptional = songService.findById(id);
        if (!songOptional.isPresent())
            return new ModelAndView("error-404");
        Song song = songOptional.get();
        return new ModelAndView("edit", "song", song);
    }

    @PostMapping("/{id}/edit")
    public String editSong(@Validated @ModelAttribute Song song, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors())
            return "edit";
        songService.save(song);
        return "redirect:/songs/list";
    }
}
