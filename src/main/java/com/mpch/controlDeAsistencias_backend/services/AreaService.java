package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaService {

    Area addArea(Area area) throws Exception;

    Area getAreaById(Long idArea);

    Page<Area> getAllAreas(Pageable pageable);

    Page<Area> searchAreaByName(String name, Pageable pageable);

    Long getTotalAreas();

    Area updateArea(Long idArea, Area area);

    void deleteArea(Long idArea);

}
