package com.springboot.wms.restapi.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "advicedetails"
)
public class AdviceDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "totalPrice", nullable = false)
    private double totalPrice;

    @Column(name = "totalQuantity", nullable = false)
    private int totalQuantity;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adviceID", referencedColumnName = "id")
    private Advice advice;
}
