package com.sahibinden.arac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","users"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long roleId;

    @Column(name = "name", columnDefinition = "nvarchar(50)")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<AppUser> users;

    public Role(long roleId) {
        this.roleId = roleId;
    }

    public Role(long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
