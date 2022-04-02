package com.springboot.wms.restapi.restapi.Inventory;

import com.springboot.wms.restapi.restapi.Order.Order;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
import com.springboot.wms.restapi.restapi.InventoryLoadUnit.InventoryLoadUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sku_quantity_units_id")
    private SkuQuantityUnit sku_quantity_unit;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InventoryLoadUnit> inventory_units = new HashSet<>();


}
