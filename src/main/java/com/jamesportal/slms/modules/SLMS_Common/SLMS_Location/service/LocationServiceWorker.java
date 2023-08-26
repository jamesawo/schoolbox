
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.dto.StateDTO;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Country;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Lga;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceWorker {
    @Autowired
    LocationLgaServiceImpl lgaService;

    @Autowired
    LocationStateServiceImpl stateService;

    @Autowired
    CountryServiceImpl countryService;

    public void createLocationStateByList(StateDTO stateDTO){
        stateService.createLocationStateFromList(stateDTO.getStateList());
    }

    public List<Lga> getLgaListByState(StateDTO stateDTO){

        return lgaService.getLocationLgaByState(stateDTO.getState());
    }

    public List<State> getAllLocationState(){
        return stateService.getAllLocationState();
    }

    public List<Country> getAllLocationCountry(){
        return countryService.findAll();
    }


}
