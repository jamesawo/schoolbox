
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.dto;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.Lga;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LgaDTO {
    private long id;

    private String name;

    private State state;

    private List<Lga> lgaList;

    private List<State> stateList;

}
