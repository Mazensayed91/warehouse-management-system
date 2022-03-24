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
        name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Role {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeID", referencedColumnName = "id")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplierID", referencedColumnName = "id")
    private Supplier supplier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany
    private Set<Permission> permissions;
}
