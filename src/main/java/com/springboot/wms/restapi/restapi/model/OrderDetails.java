package com.springboot.wms.restapi.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "orderdetails"
)
public class OrderDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderID", referencedColumnName = "id")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaseID", referencedColumnName = "id")
    private Purchase purchase;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discountID", referencedColumnName = "id")
    private Discount discount;
}
