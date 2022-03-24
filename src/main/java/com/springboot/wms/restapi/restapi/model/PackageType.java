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
        name = "packagetypes"
)
public class PackageType {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Dimensions dimensions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventoryUnitDetailsID", referencedColumnName = "id")
    private InventoryUnitDetails inventoryUnitDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adviceLineUnitDetailsID", referencedColumnName = "id")
    private AdviceLineUnitDetails adviceLineUnitDetailsID;

}
