
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity;

import com.jamesportal.slms.entity.User;
import com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.entity.FileUpload;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "SLMS_IdentityRecord")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SlmsIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;

    @OneToOne
    private User user;

    @OneToOne
    private Student student;

    @OneToMany(mappedBy = "identity")
    private List<FileUpload> file;
}
