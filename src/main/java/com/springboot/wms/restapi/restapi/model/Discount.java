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
        name = "discount"
)
public class Discount {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "discountPercentage", nullable = false)
    private int discountPercentage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetailsID", referencedColumnName = "id")
    private OrderDetails orderDetails;
}
