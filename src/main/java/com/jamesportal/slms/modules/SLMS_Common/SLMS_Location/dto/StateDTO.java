
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Lga;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;
import lombok.*;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class StateDTO {

    private List<State> stateList;

    private long id;

    private String name;

    private State state;

    private Lga lga;

    private List<Lga> lgaList;

    public StateDTO(State state) {
        this.state = state;
    }
}
