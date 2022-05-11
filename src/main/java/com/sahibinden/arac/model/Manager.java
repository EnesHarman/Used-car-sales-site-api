package com.sahibinden.arac.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "managers")
@Builder
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long managerId;

    @Column(name = "identity_number", columnDefinition = "varchar(11)")
    private String managerIdentityNumber;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private AppUser user;
}
