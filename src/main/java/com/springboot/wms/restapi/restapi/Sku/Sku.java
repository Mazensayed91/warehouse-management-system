package com.springboot.wms.restapi.restapi.Sku;

import com.springboot.wms.restapi.restapi.Category.Category;
import com.springboot.wms.restapi.restapi.SkuQuantityUnit.SkuQuantityUnit;
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
        name = Sku.TABLE_NAME
)
public class Sku {

    static final String TABLE_NAME = "SKU";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_expired", nullable = false)
    private boolean is_expired;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "sku", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SkuQuantityUnit> sku_quantity_units;
}
