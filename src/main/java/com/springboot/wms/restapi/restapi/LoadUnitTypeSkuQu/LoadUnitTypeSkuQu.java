package com.springboot.wms.restapi.restapi.LoadUnitTypeSkuQu;

import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
//        (
//        name = "load_unit_type_capacities"
//)
public class LoadUnitTypeSkuQu {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "orientation", nullable = false)
    private String orientation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sku_quantity_unit_id")
    private SkuQuantityUnit sku_quantity_unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="load_unit_type_id")
    private LoadUnitType load_unit_type;

}
