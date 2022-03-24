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
@Table
//        (
//        name = "load_unit_type_capacities"
//)
public class LoadUnitTypeCapacity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sku_ID", referencedColumnName = "id")
    private Sku sku;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="load_UnitTypeID")
    private LoadUnitType loadUnitType;

}
