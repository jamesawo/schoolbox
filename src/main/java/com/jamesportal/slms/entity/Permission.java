
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "permission_name")
    private String name;

    @Column(name = "permission_group")
    private String group;

    public Permission(String name, String group){
        this.name = name;
        this.group = group;
    }

    public Permission(){}

}
