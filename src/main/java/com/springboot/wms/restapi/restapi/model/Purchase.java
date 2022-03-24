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
        name = "purchase"
)
public class Purchase {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "paymentType", nullable = false)
    private String paymentType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderDetailsID", referencedColumnName = "id")
    private OrderDetails orderDetails;
}
