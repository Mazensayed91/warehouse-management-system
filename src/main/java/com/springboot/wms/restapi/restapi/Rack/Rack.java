package com.springboot.wms.restapi.restapi.Rack;

import com.springboot.wms.restapi.restapi.Aisle.Aisle;
import com.springboot.wms.restapi.restapi.LoadUnit.LoadUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "rack"
)
public class Rack {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "rack", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LoadUnit> load_units = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aisle_id", referencedColumnName = "id")
    private Aisle aisle;
}
