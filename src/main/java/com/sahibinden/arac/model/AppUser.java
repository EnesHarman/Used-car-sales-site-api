package com.sahibinden.arac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","manager","customer"})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long appUserId;

    @Column(name = "email", columnDefinition = "nvarchar(100)", unique = true)
    private String appUserEmail;

    @Column(name = "password", columnDefinition = "nvarchar(255)")
    private String appUserPassword;

    @Column(name = "name",columnDefinition = "varchar(50)")
    private String appUserName;

    @Column(name = "surname", columnDefinition = "varchar(50)")
    private String appUserSurname;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Manager manager;

}
