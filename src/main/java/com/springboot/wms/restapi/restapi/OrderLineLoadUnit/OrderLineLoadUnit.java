package com.springboot.wms.restapi.restapi.OrderLineLoadUnit;

import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import com.springboot.wms.restapi.restapi.OrderLine.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "order_line_load_unit"
)
public class OrderLineLoadUnit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "available_quantity", nullable = false)
    private int available_quantity;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advice_line_id", referencedColumnName = "id")
    private OrderLine order_line;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "load_unit_id", referencedColumnName = "id")
    private LoadUnit load_unit;

}
