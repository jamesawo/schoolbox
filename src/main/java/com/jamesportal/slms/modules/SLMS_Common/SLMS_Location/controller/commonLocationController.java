
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.controller;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.dto.StateDTO;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Lga;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.service.LocationServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class commonLocationController {


    private LocationServiceWorker locationServiceWorker;

    public commonLocationController(LocationServiceWorker locationServiceWorker) {
        this.locationServiceWorker = locationServiceWorker;
    }

    @GetMapping("/API/SLMS/SLMS_Common/Location/LgaList/{state}")
    public List<Lga> getGreetings(@PathVariable @Valid long state)
    {
        StateDTO stateDTO = new StateDTO();
        stateDTO.setState( new State(state));
        return  locationServiceWorker.getLgaListByState(stateDTO);
    }


}
