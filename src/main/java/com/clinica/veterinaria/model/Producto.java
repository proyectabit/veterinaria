package com.clinica.veterinaria.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "producto")

public class Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private String detalle;
    private String informacion;
    private BigDecimal precio;
    private Integer stock;
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    private String imagen;
    private Integer categoria;
    private String estado;
   
}