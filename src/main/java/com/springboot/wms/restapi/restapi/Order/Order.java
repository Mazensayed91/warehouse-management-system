package com.springboot.wms.restapi.restapi.Order;

import com.springboot.wms.restapi.restapi.Customer.Customer;
import com.springboot.wms.restapi.restapi.OrderLine.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "orders"
)
public class Order {

    public enum Status {
        PENDING, IN_PROGRESS, REJECTED, DELIVERED;
    }


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;


    @Column(name = "sub_total", nullable = false)
    private BigDecimal sub_total;

    @Column(name = "tax", nullable = false)
    private BigDecimal tax;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    @Column(name = "status", nullable = false)
    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customerID")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderLine> orderLines = new HashSet<>();

}
