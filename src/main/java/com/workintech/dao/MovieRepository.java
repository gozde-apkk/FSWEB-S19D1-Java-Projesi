package com.workintech.dao;

import com.workintech.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie , Integer> {
}
