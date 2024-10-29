package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Area;
import com.mpch.controlDeAsistencias_backend.repository.AreaRepository;
import com.mpch.controlDeAsistencias_backend.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    private void validateAreaName(String name) {
        Area area = areaRepository.findByName(name);
        if (area != null) {
            throw new RuntimeException("Area ya existente");
        }
    }

    @Override
    public Area addArea(Area area) {
        validateAreaName(area.getName());
        return areaRepository.save(area);
    }

    @Override
    public Area getAreaById(UUID idArea) {
        return areaRepository.findById(idArea).orElseThrow(
                () -> new RuntimeException("Area no encontrada")
        );
    }

    @Override
    public Page<Area> getAllAreas(Pageable pageable) {
        return areaRepository.findAll(pageable);
    }

    @Override
    public List<Area> searchAreaByName(String name, int page, int size) {
        return areaRepository.searchByName(name, page, size);
    }

    @Override
    public Long getTotalAreas() {
        return areaRepository.count();
    }

    @Override
    public Area updateArea(UUID idArea, Area area) {
        Area existingArea = getAreaById(idArea);
        if (!existingArea.getName().equals(area.getName())) {
            validateAreaName(area.getName());
        }

        existingArea.setName(area.getName());
        existingArea.setHours_certified(area.getHours_certified());
        existingArea.setNro_vacancies(area.getNro_vacancies());
        existingArea.setStatus(area.isStatus());

        return areaRepository.save(existingArea);
    }

    @Override
    public void deleteArea(UUID idArea) {
        areaRepository.deleteById(idArea);
    }
}
