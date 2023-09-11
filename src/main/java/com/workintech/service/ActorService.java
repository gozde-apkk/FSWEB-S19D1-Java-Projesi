package com.workintech.service;

import com.workintech.entity.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> getAll();
    Actor getById(int id);
    Actor save(Actor actor);
    Actor  delete(int id );
}
