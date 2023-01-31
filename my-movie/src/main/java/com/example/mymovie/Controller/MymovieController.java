package com.example.mymovie.Controller;

import com.example.mymovie.model.Acteur;
import com.example.mymovie.model.Film;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
@RestController

public class MymovieController {
    private List<Film> films;
    private List<Acteur> acteurs;

    public MymovieController() {
        acteurs = new ArrayList<>();
        Acteur acteur = new Acteur("Hamza", "Ben",  new Date());
        Acteur acteurbis = new Acteur("Hamid","Ouled", new Date());
        acteurs.add(acteur);
        acteurs.add(acteurbis);

        films = new ArrayList<>();
        Film film = new Film("film", "Tom", acteur, new Date());
        Film filmbis = new Film("serie", "John", acteurbis, new Date());
        films.add(film);
        films.add(filmbis);
    }
    @GetMapping("/acteurs")
    public List<Acteur> getActeurs() {
        return acteurs;
    }

    @GetMapping("/acteurs/{nom}")
    public Acteur getActeurByNom(@PathVariable String nom) {
        for (Acteur acteur : acteurs) {
            if (acteur.getNom().equals(nom)) {
                return acteur;
            }
        }
        return null;
    }
    @GetMapping("/acteurs/film/{titre}")
    public List<Acteur> getActeursByFilm(@PathVariable String titre) {
        List<Acteur> acteursByFilm = new ArrayList<>();
        for (Film film : films) {
            if (film.getTitre().equals(titre)) {
                acteursByFilm.add(film.getActeurPrincipal());
                break;
            }
        }
        return acteursByFilm;
    }

    @GetMapping("/films")
    public List<Film> getFilms() {
        return films;
    }

    @GetMapping("/films/{titre}")
    public Film getFilmByTitre(@PathVariable String titre) {
        for (Film film : films) {
            if (film.getTitre().equals(titre)) {
                return film;
            }
        }
        return null;
    }
    


}
