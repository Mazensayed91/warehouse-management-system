package com.springboot.wms.restapi.restapi.InventoryLoadUnit;

import com.springboot.wms.restapi.restapi.Inventory.Inventory;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "inventory_load_units"
)
public class InventoryLoadUnit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="inventory_id")
    private Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="load_unit_id")
    private LoadUnit load_unit;

}
