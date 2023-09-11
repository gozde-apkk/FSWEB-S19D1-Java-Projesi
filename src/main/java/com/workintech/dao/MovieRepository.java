package com.workintech.dao;

import com.workintech.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface MovieRepository extends JpaRepository<Movie , Integer> {
}
