package com.springboot.wms.restapi.restapi.LoadUnit;

import com.springboot.wms.restapi.restapi.InventoryLoadUnit.InventoryLoadUnit;
import com.springboot.wms.restapi.restapi.LoadUnitType.LoadUnitType;
import com.springboot.wms.restapi.restapi.Rack.Rack;
import com.springboot.wms.restapi.restapi.AdviceLineLoadUnit.AdviceLineLoadUnit;
import com.springboot.wms.restapi.restapi.OrderLineLoadUnit.OrderLineLoadUnit;
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
        name = "load_unit"
)
public class LoadUnit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @OneToMany(mappedBy = "load_unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InventoryLoadUnit> inventory_load_units;

    @OneToMany(mappedBy = "load_unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdviceLineLoadUnit> advice_line_load_unit_id;

    @OneToMany(mappedBy = "load_unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderLineLoadUnit> order_line_load_unit_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "load_unit_type_id", referencedColumnName = "id")
    private LoadUnitType load_unit_type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rack", referencedColumnName = "id")
    private Rack rack;

}
