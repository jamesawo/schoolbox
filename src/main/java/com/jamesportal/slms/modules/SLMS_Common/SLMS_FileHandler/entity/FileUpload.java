
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_Common.SLMS_FileHandler.entity;

import com.jamesportal.slms.modules.SLMS_Common.SLMS_Identity.entity.SlmsIdentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name="SLMS_FilesRecord")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String name;

    private String type;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] data;

    @ManyToOne
    @JoinColumn
    private SlmsIdentity identity;

    public FileUpload(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

}
