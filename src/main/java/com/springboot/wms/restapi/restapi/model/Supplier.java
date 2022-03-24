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
        name = "suppliers"
)
public class Supplier {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Embedded
    private ContactPerson contactPerson;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Advice> advices;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleID", referencedColumnName = "id")
    private Role role;
}
