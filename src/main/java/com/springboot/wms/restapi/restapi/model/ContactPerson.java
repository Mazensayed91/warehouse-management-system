package com.springboot.wms.restapi.restapi.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class ContactPerson {

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "number", nullable = false)
    private String number;
}