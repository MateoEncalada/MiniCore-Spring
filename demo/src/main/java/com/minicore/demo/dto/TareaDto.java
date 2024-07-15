package com.minicore.demo.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TareaDto {
    private Long idEmpleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String tarea;
    private Date fechaInicio;
    private Date fechaEstimado;
    private Integer estimado;
    private String proyecto;
    private Long diasPasados;

    // Método builder para apellidoEmpleado
    public static class TareaDtoBuilder {
        private String apellidoEmpleado;

        public TareaDtoBuilder apellidoEmpleado(String apellidoEmpleado) {
            this.apellidoEmpleado = apellidoEmpleado;
            return this;
        }
    }
}
