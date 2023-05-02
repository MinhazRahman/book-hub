package com.bookhub.service;

import com.bookhub.model.State;
import com.bookhub.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService{
    private final StateRepository stateRepository;

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public State findById(Integer id) {
        return stateRepository.findById(id).orElseThrow();
    }

    @Override
    public List<State> findByCountryCode(String code) {
        return stateRepository.findByCountryCode(code);
    }

    @Override
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State update(Integer id, State state) {
        State existingState = stateRepository.findById(id).orElseThrow();

        existingState.setName(state.getName());
        // save the Book, if id == 0, then save/insert otherwise update
        return stateRepository.save(existingState);
    }

    @Override
    public void deleteById(Integer id) {
        stateRepository.deleteById(id);
    }
}
