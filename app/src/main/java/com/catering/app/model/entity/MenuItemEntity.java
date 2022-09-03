package com.catering.app.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
public class MenuItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight_output")
    private Integer weightOutput;

    @Column(name = "coast")
    private Integer coast;
}
