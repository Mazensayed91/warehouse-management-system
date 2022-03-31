package com.springboot.wms.restapi.restapi.OrderLine;

import com.springboot.wms.restapi.restapi.Order.Order;
import com.springboot.wms.restapi.restapi.OrderLineLoadUnit.OrderLineLoadUnit;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
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
        name = "order_lines"
)
public class OrderLine {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "price_before", nullable = false)
    private double price_before;

    @Column(name = "price_after", nullable = false)
    private double price_after;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @OneToMany(mappedBy = "order_line", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderLineLoadUnit> order_line_load_units;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sku_quantity_unit_id", referencedColumnName = "id")
    private SkuQuantityUnit sku_quantity_unit;

}
