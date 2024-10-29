package com.mpch.controlDeAsistencias_backend.services;

import com.mpch.controlDeAsistencias_backend.model.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AreaService {

    Area addArea(Area area);

    Area getAreaById(UUID idArea);

    Page<Area> getAllAreas(Pageable pageable);

    List<Area> searchAreaByName(String name, int page, int size);

    Long getTotalAreas();

    Area updateArea(UUID idArea, Area area);

    void deleteArea(UUID idArea);

}
