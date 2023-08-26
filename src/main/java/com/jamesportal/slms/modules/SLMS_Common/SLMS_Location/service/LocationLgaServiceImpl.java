
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
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.repository.LgaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationLgaServiceImpl implements ILocationLgaService {
    @Autowired
    LgaRepository lgaRepository;

    @Override
    public void saveLocationLga(Lga lga) {
        lgaRepository.save(lga);
    }

    @Override
    public void createLocationLgaFromList(List<Lga> lgaList) {
        lgaRepository.saveAll(lgaList);
    }

    @Override
    public List<Lga> getAllLocationLga() {
        return lgaRepository.findAll();
    }

    @Override
    public List<Lga> getLocationLgaByState(State state) {
        return lgaRepository.findAllByState(state);
    }
}
