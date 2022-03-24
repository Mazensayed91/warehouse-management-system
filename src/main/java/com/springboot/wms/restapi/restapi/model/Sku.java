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
        name = Sku.TABLE_NAME
)
public class Sku {

    static final String TABLE_NAME = "SKU";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inventoryID")
    private Inventory inventory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryID", referencedColumnName = "id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loadUnitTypeCapacityID", referencedColumnName = "id")
    private LoadUnitTypeCapacity loadUnitTypeCapacity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adviceLineID", referencedColumnName = "id")
    private AdviceLine adviceLine;




}
