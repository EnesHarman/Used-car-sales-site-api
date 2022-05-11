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
@Table(name = "addresses")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","customer"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long addressId;

    @Column(name = "info", columnDefinition = "text")
    private String addressInfo;

    @OneToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
