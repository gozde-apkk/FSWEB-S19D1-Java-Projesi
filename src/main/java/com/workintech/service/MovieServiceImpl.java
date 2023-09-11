package com.workintech.service;

import com.workintech.dao.MovieRepository;
import com.workintech.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()){
            return movie.get();
        }
        //TODO
        return null;
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie delete(int id) {
        Movie movie = getById(id);
        movieRepository.delete(movie);
        return movie;
    }
}
