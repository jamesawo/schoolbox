
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.service;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Lga;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;

import java.util.List;

public interface ILocationLgaService {

    void saveLocationLga(Lga lga);

    void createLocationLgaFromList(List<Lga> lgaList);

    List<Lga> getAllLocationLga();

    List<Lga> getLocationLgaByState(State state);


}
