package com.springboot.wms.restapi.restapi.AdviceLine;

import com.springboot.wms.restapi.restapi.Advice.Advice;
import com.springboot.wms.restapi.restapi.AdviceLineLoadUnit.AdviceLineLoadUnit;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "advice_lines"
)
public class AdviceLine {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "price_before", nullable = false)
    private double price_before;

    @Column(name = "price_after", nullable = false)
    private double price_after;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "expire_date", nullable = false)
    private Date expire_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="advice_id")
    private Advice advice;

    @OneToMany(mappedBy = "advice_line", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdviceLineLoadUnit> advice_line_load_units = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sku_quantity_unit_id", referencedColumnName = "id")
    private SkuQuantityUnit sku_quantity_unit;
}
