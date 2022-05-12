package com.sahibinden.arac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","comments","vehicles"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long customerId;

    @Column(name = "register_date")
    private LocalDateTime customerRegisterDate;

    @Column(name = "phone_number", columnDefinition = "varchar(20)")
    private String customerPhoneNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private AppUser user;

    @OneToOne(mappedBy = "customer" ,cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Customer(long customerId) {
        this.customerId = customerId;
    }
}
