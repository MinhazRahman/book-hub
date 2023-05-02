package com.bookhub.controller;

import com.bookhub.model.State;
import com.bookhub.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/bookhub")
@RequiredArgsConstructor
public class StateController {
    private final StateService stateService;

    @GetMapping("/states")
    public List<State> getStates(){
        return stateService.findAll();
    }


    @GetMapping("/states/{stateId}")
    public State getStates(@PathVariable(name = "stateId") Integer stateId){
        State existingState = stateService.findById(stateId);
        if (existingState == null){
            throw new RuntimeException("State not found with id - " + stateId);
        }
        return existingState;
    }


    @GetMapping("/states/findByCountryCode/{countryCode}")
    public List<State> getStatesByCountryCode(@PathVariable(name = "countryCode") String countryCode){
       List<State> states = stateService.findByCountryCode(countryCode);

        if (states == null){
            throw new RuntimeException("States don't belong to the country with code - " + countryCode);
        }
        return states;
    }
}
