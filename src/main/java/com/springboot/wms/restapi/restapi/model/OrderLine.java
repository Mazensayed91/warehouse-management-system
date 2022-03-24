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
        name = "orderlines"
)
public class OrderLine {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "unitPrice", nullable = false)
    private double unitPrice;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="orderID")
    private Order order;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderLineID", referencedColumnName = "id")
    private OrderLine orderLine;

}
