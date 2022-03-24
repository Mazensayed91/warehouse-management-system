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
        name = "loadunit"
)
public class LoadUnit {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "columnNumber", nullable = false)
    private int columnNumber;

    @Column(name = "rowNumber", nullable = false)
    private int rowNumber;

    @OneToMany(mappedBy = "loadUnit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inventory> inventories;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loadUnitTypeID", referencedColumnName = "id")
    private LoadUnitType loadUnitType;

}
