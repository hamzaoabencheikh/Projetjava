package com.example.mymovie.Controller;

import com.example.mymovie.model.Acteur;
import com.example.mymovie.model.Film;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MymovieController {
    private List<Film> films;
    private List<Acteur> acteurs;

    public MymovieController() throws ParseException {

        acteurs = new ArrayList<>();
        Acteur acteur = new Acteur("Ben", "Hamza", "1994-01-05");
        Acteur acteurbis = new Acteur("Ouled", "Hamid", "1994-02-05");
        acteurs.add(acteur);
        acteurs.add(acteurbis);

        films = new ArrayList<>();
        Film film = new Film("Film", "Karim", acteur,"2012-12-12");
        Film filmbis = new Film("MiniFilm", "Omar", acteurbis, "2018-10-01");
        films.add(film);
        films.add(filmbis);
    }

    @ApiOperation(value = "Get list of Films acteurs in the System ", response = Iterable.class, tags = "getActeurs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess !"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/acteurs")
    public List<Acteur> getActeurs() {
        return acteurs;
    }

    @ApiOperation(value = "Get list of acteurs by nom in the System ", response = Iterable.class, tags = "getActeurs by nom")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess !"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/acteurs/{nom}")
    public Acteur getActeurByNom(@PathVariable String nom) {
        for (Acteur acteur : acteurs) {
            if (acteur.getNom().equals(nom)) {
                return acteur;
            }
        }
        return null;
    }

    @ApiOperation(value = "Get list of acteurs by nom in the System ", response = Iterable.class, tags = "getActeurs by film/titre")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/acteurs/film/{titre}")
    public List<Acteur> getActeursByFilm(@PathVariable String titre) {
        List<Acteur> acteursByFilm = new ArrayList<>();
        for (Film film : films) {
            if (film.getTitle().equals(titre)) {
                acteursByFilm.add(film.getActeurPrincipal());
                break;
            }
        }
        return acteursByFilm;
    }
    @ApiOperation(value = "Get list of films ", response = Iterable.class, tags = "getfilms")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/films")
    public List<Film> getFilms() {
        return films;
    }

    @ApiOperation(value = "Get list of film  by title in the System ", response = Iterable.class, tags = "getActeurs by title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/films/{title}")
    public Film getFilmByTitre(@PathVariable String title) {
        for (Film film : films) {
            if (film.getTitle().equals(title)) {
                return film;
            }
        }
        return null;
    }


    @ApiOperation(value = "Get list of films by anne in the System ", response = Iterable.class, tags = "getfilms by anne")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/films/{annee}")
    public Film getFilmByAnnee(@PathVariable String datesortie) {
        for (Film film : films) {
            if (film.getDatesortie().equals(datesortie)) {
                return film;
            }
        }
        return null;
    }




}
