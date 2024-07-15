package com.minicore.demo.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_estimado", nullable = false)
    private Date fechaEstimado; // Campo para la fecha estimada de finalización

    @Column(name = "estimado", nullable = false)
    private Integer estimado; // Días estimados para completar la tarea

    private String estado; // "EnProgreso", "Terminado"

    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

    // Método getter para estimado
    public Integer getEstimado() {
        return estimado;
    }

    // Método getter para fecha estimada
    public Date getFechaEstimado() {
        return fechaEstimado;
    }
}

