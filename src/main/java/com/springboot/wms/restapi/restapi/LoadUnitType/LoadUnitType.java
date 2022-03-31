package com.springboot.wms.restapi.restapi.LoadUnitType;

import com.springboot.wms.restapi.restapi.Dimensions.Dimensions;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
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
        name = "loadunittypes"
)
public class LoadUnitType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Dimensions dimensions;

    @OneToMany(mappedBy = "load_unit_type", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LoadUnit> load_units;


}
