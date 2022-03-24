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
        name = "loadunittypes"
)
public class LoadUnitType {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Embedded
    private Dimensions dimensions;

    @OneToMany(mappedBy = "loadUnitType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LoadUnitTypeCapacity> loadUnitTypeCapacities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loadUnitID", referencedColumnName = "id")
    private LoadUnit loadUnit;

}
