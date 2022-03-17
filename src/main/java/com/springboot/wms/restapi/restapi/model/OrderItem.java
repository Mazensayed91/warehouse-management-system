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
        name = "orders",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class OrderItem {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="skuID")
    private Sku sku;
}
