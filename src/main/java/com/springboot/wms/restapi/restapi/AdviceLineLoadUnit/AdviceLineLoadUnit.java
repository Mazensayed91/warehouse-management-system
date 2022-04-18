package com.springboot.wms.restapi.restapi.AdviceLineLoadUnit;

import com.springboot.wms.restapi.restapi.AdviceLine.AdviceLine;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "advice_line_load_unit"
)
public class AdviceLineLoadUnit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "available_quantity", nullable = false)
    private int available_quantity;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "advice_line_id", referencedColumnName = "id")
    private AdviceLine adviceLine;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "load_unit_id", referencedColumnName = "id")
    private LoadUnit loadUnit;

}
