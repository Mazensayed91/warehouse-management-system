package com.springboot.wms.restapi.restapi.Advice;

import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLine;
import com.springboot.wms.restapi.restapi.Employee.Employee;
import com.springboot.wms.restapi.restapi.Supplier.Supplier;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "advices"
)
public class Advice {

    public enum Status {
        RECEIVED, IN_PROGRESS, REJECTED, FINISHED;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal sub_total;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "advice", cascade = CascadeType.MERGE, orphanRemoval = true)
    @Fetch(value= FetchMode.SELECT)
    private List<AdviceLine> adviceLines;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplierID")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employeeID")
    private Employee employee;
}
