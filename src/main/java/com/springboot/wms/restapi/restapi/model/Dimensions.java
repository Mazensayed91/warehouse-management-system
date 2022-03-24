package com.springboot.wms.restapi.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Dimensions {

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "width", nullable = false)
    private double width;

    @Column(name = "length", nullable = false)
    private double length;
}