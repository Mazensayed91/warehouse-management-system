package com.springboot.wms.restapi.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "loaditems"
)
public class LoadItem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "width", nullable = false)
    private double width;

    @Column(name = "length", nullable = false)
    private double length;

    @Column(name = "columnNumber", nullable = false)
    private int columnNumber;

    @Column(name = "rowNumber", nullable = false)
    private int rowNumber;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
