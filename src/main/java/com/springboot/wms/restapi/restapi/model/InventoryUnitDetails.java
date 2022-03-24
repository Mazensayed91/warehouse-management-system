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
        name = "inventoryunitdetails"
)
public class InventoryUnitDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "packageTypeID", referencedColumnName = "id")
    private PackageType packageType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventoryID", referencedColumnName = "id")
    private Inventory inventory;

}
