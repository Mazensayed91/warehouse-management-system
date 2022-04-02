package com.springboot.wms.restapi.restapi.SkuQuantityUnit;

import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLine;
import com.springboot.wms.restapi.restapi.Dimensions.Dimensions;
import com.springboot.wms.restapi.restapi.OrderLine.OrderLine;
import com.springboot.wms.restapi.restapi.Inventory.Inventory;
import com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu.LoadUnitTypeSkuQu;
import com.springboot.wms.restapi.restapi.Sku.Sku;
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
        name = "sku_quantity_units"
)
public class SkuQuantityUnit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "singular_quantity", nullable = false)
    private int singular_quantity;

    @Embedded
    private Dimensions dimensions;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "load_unit_type_sku_qu_id", referencedColumnName = "id")
    private LoadUnitTypeSkuQu load_unit_type_sku_qu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advice_line_id", referencedColumnName = "id")
    private AdviceLine advice_line;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_line_id", referencedColumnName = "id")
    private OrderLine order_line;


    @OneToMany(mappedBy = "sku_quantity_unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inventory> inventories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sku_id")
    private Sku sku;

}
