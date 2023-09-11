package com.workintech.controller;

import com.workintech.dao.MovieRepository;
import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieActorRequest;
import com.workintech.dto.MovieActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Actor;
import com.workintech.entity.Movie;
import com.workintech.service.MovieService;
import com.workintech.util.HollywoodUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public List<MovieResponse> finnAll(){
        List<MovieResponse> movieResponses = new ArrayList<>();
        List<Movie> movies = movieService.getAll();
        for (Movie movie : movies){
            movieResponses.add(new MovieResponse(movie.getId(), movie.getName() , movie.getDirectorName(),
                    movie.getRating() , movie.getReleaseDate()));
        }
        return movieResponses;
    }


    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable int id) {
        Movie foundMovie = movieService.getById(id);
        return new MovieResponse(foundMovie.getId(), foundMovie.getName(), foundMovie.getDirectorName(),
                foundMovie.getRating(), foundMovie.getReleaseDate());

    }

    @PostMapping("/")
    public MovieActorResponse save(@RequestBody MovieActorRequest movieActorRequest) {
        Movie movie = movieActorRequest.getMovie();
        Actor actor = movieActorRequest.getActor();
        movie.addActor(actor);
        Movie savedMovie = movieService.save(movie);
        return HollywoodUtility.convertMovieActorResponse(savedMovie, actor);
    }

    @PostMapping("/addActor/{movieId}")
    public List<ActorResponse> addActor(@RequestBody List<Actor> actors, @PathVariable int movieId) {
        Movie movie = movieService.getById(movieId);
        movie.addAllActor(actors);
        Movie savedMovie = movieService.save(movie);
        return HollywoodUtility.convertActorResponses(savedMovie);
    }


    @PutMapping("/{id}")
    public MovieResponse update(@RequestBody Movie movie, @PathVariable int id) {
        Movie foundMovie = movieService.getById(id);
        movie.setId(id);
        movie.setActors(foundMovie.getActors());
        Movie updatedMovie = movieService.save(movie);
        return new MovieResponse(movie.getId(), movie.getName(), movie.getDirectorName(),
                movie.getRating(), movie.getReleaseDate());
    }

    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable int id) {
        Movie movie = movieService.delete(id);
        return new MovieResponse(movie.getId(), movie.getName(), movie.getDirectorName(),
                movie.getRating(), movie.getReleaseDate());
    }

}
