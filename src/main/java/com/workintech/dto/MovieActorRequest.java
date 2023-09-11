package com.workintech.dto;

import com.workintech.entity.Actor;
import com.workintech.entity.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieActorRequest {
    private Movie movie;
    private Actor actor;
}
