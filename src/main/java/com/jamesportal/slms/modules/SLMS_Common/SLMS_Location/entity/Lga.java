
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Location.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SLMS_LocationLga_Record")
public class Lga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String description;


    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonBackReference
    private State state;

    public Lga(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Lga{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }
}
