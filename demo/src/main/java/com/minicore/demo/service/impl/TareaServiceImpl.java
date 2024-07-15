package com.minicore.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minicore.demo.dto.TareaDto;
import com.minicore.demo.models.Tarea;
import com.minicore.demo.repository.TareaRepository;
import com.minicore.demo.service.TareaService;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public List<TareaDto> obtenerTareasPasadasEnProgreso(Date fechaInicio, Date fechaFin) {
        List<Tarea> tareas = tareaRepository.findAll();
        List<TareaDto> resultado = new ArrayList<>();

        for (Tarea tarea : tareas) {
            if (tarea.getEstado().equals("En progreso") && tarea.getFechaInicio().compareTo(fechaInicio) >= 0 && tarea.getFechaInicio().compareTo(fechaFin) <= 0) {
                Date fechaEstimado = new Date(tarea.getFechaInicio().getTime() + (tarea.getEstimado() * 24L * 60L * 60L * 1000L));
                long diffInMillies = Math.max(fechaFin.getTime() - fechaEstimado.getTime(), 0); // Cambiar la fecha actual por fechaFin
                long diasPasados = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                if (fechaEstimado.before(fechaFin)) { // Cambiar la fecha actual por fechaFin
                    TareaDto tareaDto = TareaDto.builder()
                            .idEmpleado(tarea.getEmpleado().getId())
                            .nombreEmpleado(tarea.getEmpleado().getNombre())
                            .apellidoEmpleado(tarea.getEmpleado().getApellido())
                            .tarea(tarea.getDescripcion())
                            .fechaInicio(tarea.getFechaInicio())
                            .fechaEstimado(fechaEstimado)
                            .estimado(tarea.getEstimado())
                            .proyecto(tarea.getProyecto().getNombre())
                            .diasPasados(diasPasados)
                            .build();
                    resultado.add(tareaDto);
                }
            }
        }

        return resultado;
    }
}


// package com.minicore.demo.service.impl;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.concurrent.TimeUnit;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.minicore.demo.dto.TareaDto;
// import com.minicore.demo.models.Tarea;
// import com.minicore.demo.repository.TareaRepository;
// import com.minicore.demo.service.TareaService;

// @Service
// public class TareaServiceImpl implements TareaService {

//     @Autowired
//     private TareaRepository tareaRepository;

//     @Override
//     public List<TareaDto> obtenerTareasPasadasEnProgreso(Date fechaInicio, Date fechaFin) {
//         List<Tarea> tareas = tareaRepository.findAll();
//         List<TareaDto> resultado = new ArrayList<>();

//         for (Tarea tarea : tareas) {
//             if (tarea.getEstado().equals("En progreso") && 
//                 !tarea.getFechaInicio().before(fechaInicio) && 
//                 !tarea.getFechaInicio().after(fechaFin)) {
                
//                 // Verificar si la fecha estimada ya pasó
//                 if (tarea.getFechaEstimado().before(new Date())) {
//                     // Calcular los días pasados desde la fecha estimada de finalización
//                     long diasPasados = TimeUnit.MILLISECONDS.toDays(new Date().getTime() - tarea.getFechaEstimado().getTime());
//                     TareaDto tareaDto = TareaDto.builder()
//                             .idEmpleado(tarea.getEmpleado().getId())
//                             .nombreEmpleado(tarea.getEmpleado().getNombre() + " " + tarea.getEmpleado().getApellido())
//                             .tarea(tarea.getDescripcion())
//                             .fechaInicio(tarea.getFechaInicio())
//                             .fechaEstimado(tarea.getFechaEstimado())
//                             .proyecto(tarea.getProyecto().getNombre())
//                             .diasPasados(diasPasados)
//                             .build();
//                     resultado.add(tareaDto);
//                 }
//             }
//         }

//         return resultado;
//     }
// }
