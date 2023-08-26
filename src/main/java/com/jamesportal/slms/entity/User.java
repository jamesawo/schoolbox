
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * TODO::IMPLEMENT SOFT DELETE
 */
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;


    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Setter(AccessLevel.NONE)
    @Column(name = "password")
    private String password;


    @Column(name = "status")
    @Setter(AccessLevel.NONE)
    private Boolean status = false;


    @ManyToMany(cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Column(name = "lastLogin")
    private LocalDateTime lastLogin;

    public void setPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password = bCryptPasswordEncoder.encode(password);
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User( long id) {
        this.id = id;
    }

}
