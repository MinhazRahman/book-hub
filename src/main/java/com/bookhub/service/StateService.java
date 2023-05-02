package com.bookhub.service;

import com.bookhub.model.State;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StateService {
    List<State> findAll();
    State findById(Integer id);
    List<State> findByCountryCode(String code);
    State save(State state);
    State update(Integer id, State state);
    void deleteById(Integer id);
}
