package com.shadai.petowner.domain;

import java.io.Serializable;

import io.micronaut.data.annotation.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.Data;

@Data
@Entity
@Table(name = "owner_address", schema = "petowner")
public class Address implements Serializable{

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "adress")
    String address;

    @Column(name = "city")
    String city;

    @Column(name = "telephone")
    String telephone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    Owner owner;
}
