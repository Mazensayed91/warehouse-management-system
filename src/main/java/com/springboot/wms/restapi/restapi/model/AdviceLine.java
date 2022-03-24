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
        name = "advicelines"
)
public class AdviceLine {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "unitPrice", nullable = false)
    private double unitPrice;

    @OneToMany(mappedBy = "adviceLine", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdviceLineUnitDetails> adviceLineUnitDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adviceID")
    private Advice advice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skuID", referencedColumnName = "id")
    private Sku sku;
}
