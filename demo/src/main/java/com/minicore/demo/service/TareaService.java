package com.minicore.demo.service;

import java.util.Date;
import java.util.List;
import com.minicore.demo.dto.TareaDto;

public interface TareaService {
    List<TareaDto> obtenerTareasPasadasEnProgreso(Date fechaInicio, Date fechaFin);
}
