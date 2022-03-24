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
        name = "advices"
)
public class Advice {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @OneToMany(mappedBy = "advice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdviceLine> adviceLines;

    @ManyToMany
    private Set<Status> statuses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplierID")
    private Supplier supplier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adviceDetailsID", referencedColumnName = "id")
    private AdviceDetails adviceDetails;
}
