package com.springboot.wms.restapi.restapi.Inventory;

import com.springboot.wms.restapi.restapi.Order.Order;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
import com.springboot.wms.restapi.restapi.InventoryLoadUnit.InventoryLoadUnit;
import com.springboot.wms.restapi.restapi.Supplier.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "count_global")
    private int count_global;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sku_quantity_unit_id")
    private SkuQuantityUnit skuQuantityUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InventoryLoadUnit> inventory_units = new HashSet<>();


}
