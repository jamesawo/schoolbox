
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationStateServiceImpl implements ILocationStateService {
    @Autowired
    StateRepository stateRepository;

    @Override
    public State createLocationState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public void createLocationStateFromList(List<State> states) {
        stateRepository.saveAll(states);
    }

    @Override
    public List<State> getAllLocationState() {
        return stateRepository.findAll();
    }
}
