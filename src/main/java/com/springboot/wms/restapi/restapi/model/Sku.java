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
        name = "skus"
)
public class Sku {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "width", nullable = false)
    private double width;

    @Column(name = "length", nullable = false)
    private double length;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="loadItemID")
    private LoadItem loadItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplierID")
    private Supplier supplier;

    @OneToMany(mappedBy = "sku", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

}
