package com.workintech.service;

import com.workintech.entity.Actor;
import com.workintech.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll();
    Movie getById(int id);
    Movie save(Movie movie);
    Movie  delete(int id );
}
