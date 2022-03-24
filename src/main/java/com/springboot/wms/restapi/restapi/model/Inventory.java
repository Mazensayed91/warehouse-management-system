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
        name = "inventories"
)
public class Inventory {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventoryUnitDetailsID", referencedColumnName = "id")
    private Inventory inventoryUnitDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderLineID", referencedColumnName = "id")
    private OrderLine orderLine;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sku> skus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="loadUnitID")
    private LoadUnit loadUnit;

}
