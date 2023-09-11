package com.workintech.controller;


import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieActorRequest;
import com.workintech.dto.MovieActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Actor;
import com.workintech.entity.Movie;
import com.workintech.service.ActorService;
import com.workintech.util.HollywoodUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private ActorService actorService;
    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }


    //response dön

    @GetMapping("/")
    public List<ActorResponse> getAll(){
        List<ActorResponse> actorResponses = new ArrayList<>();
        List<Actor> actors = actorService.getAll();
        for(Actor actor : actors){
            actorResponses.add(new ActorResponse(actor.getId() , actor.getFirstName(),
                    actor.getLastName(), actor.getBirthDate()));
        }
        return actorResponses;
    }

    @GetMapping("/{id}")
    public ActorResponse getBtId(@PathVariable int id){
        Actor foundActor = actorService.getById(id);
        return new ActorResponse(foundActor.getId() , foundActor.getFirstName(),
                foundActor.getLastName(),foundActor.getBirthDate());
    }

    @PostMapping("/")
    public MovieActorResponse save(@RequestBody MovieActorRequest movieActorRequest){
        Movie movie = movieActorRequest.getMovie();
        Actor actor = movieActorRequest.getActor();
        movie.addActor(actor);
        Actor savedActor = actorService.save(actor);
        return HollywoodUtility.convertMovieActorResponse(movie , savedActor);
    }

    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor , @PathVariable int id){
        Actor foundActor = actorService.getById(id);
        actor.setId(id);
        actor.setMovies(foundActor.getMovies());
        Actor updateActor = actorService.save(actor);
        return new ActorResponse(actor.getId() , actor.getFirstName(),
                actor.getLastName(), actor.getBirthDate());
    }


    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable int id) {
        Actor actor = actorService.delete(id);
        return new ActorResponse(actor.getId(), actor.getFirstName(),
                actor.getLastName(), actor.getBirthDate());
    }
}


