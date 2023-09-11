package com.workintech.service;

import com.workintech.dao.ActorRepository;
import com.workintech.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ActorServiceImpl implements  ActorService{

    private ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor getById(int id) {
        Optional<Actor> actor = actorRepository.findById(id);
        if (actor.isPresent()){
            return actor.get();
        }
        //TODO
        return null;
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor delete(int id) {
        Actor actor = getById(id);
        actorRepository.delete(actor);
        return  actor;
    }
}
