
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;

import java.util.List;

public interface ILocationStateService {

    State createLocationState(State state);

    void createLocationStateFromList(List<State> states);

    List<State> getAllLocationState();


}
