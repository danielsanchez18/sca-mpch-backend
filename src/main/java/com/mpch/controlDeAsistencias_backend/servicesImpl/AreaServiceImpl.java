package com.mpch.controlDeAsistencias_backend.servicesImpl;

import com.mpch.controlDeAsistencias_backend.model.Area;
import com.mpch.controlDeAsistencias_backend.repository.AreaRepository;
import com.mpch.controlDeAsistencias_backend.repository.InternRepository;
import com.mpch.controlDeAsistencias_backend.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private InternRepository internRepository;

    // Validation: Unique name (create)
    private void validateUniqueAreaName(String name) {
        if (areaRepository.existsByName(name)) {
            throw new RuntimeException("El área con el nombre '" + name + "' ya existe.");
        }
    }

    // Validation: Unique name (update)
    private void validateUniqueAreaName(String name, Long id) {
        Optional<Area> areaWithName = areaRepository.findByName(name);
        if (areaWithName.isPresent() && !areaWithName.get().getIdArea().equals(id)) {
            throw new RuntimeException("El área con el nombre '" + name + "' ya existe.");
        }
    }

    // Validation: Existing area
    private void validateExistingArea(Long id) {
        if (!areaRepository.existsById(id)) {
            throw new IllegalArgumentException("Área no encontrada con el ID " + id);
        }
    }

    // Validation: Cannot be eliminated if there is an intern
    private void validateAreaInUse(Long id) {
        boolean isAreaInUse = internRepository.existsByAreaUniversity_Area_IdArea(id);
        if (isAreaInUse) {
            throw new RuntimeException("El área no se puede eliminar porque está en uso.");
        }
    }

    @Override
    public Area addArea(Area area) throws Exception {
        validateUniqueAreaName(area.getName());
        return areaRepository.save(area);
    }

    @Override
    public Area getAreaById(Long idArea) {
        return areaRepository.findById(idArea).orElseThrow(
                () -> new RuntimeException("Area no encontrada")
        );
    }

    @Override
    public Page<Area> getAllAreas(Pageable pageable) {
        return areaRepository.findAll(pageable);
    }

    @Override
    public Page<Area> searchAreaByName(String name, Pageable pageable) {
        return areaRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Long getTotalAreas() {
        return areaRepository.count();
    }

    @Override
    public Area updateArea(Long idArea, Area area) {
        validateExistingArea(idArea);
        validateUniqueAreaName(area.getName(), idArea);

        Area existingArea = areaRepository.findById(idArea).orElseThrow();
        existingArea.setName(area.getName());
        existingArea.setNroVacancies(area.getNroVacancies());
        existingArea.setStatus(area.isStatus());

        return areaRepository.save(existingArea);
    }

    @Override
    public void deleteArea(Long idArea) {
        validateExistingArea(idArea);
        validateAreaInUse(idArea);

        areaRepository.deleteById(idArea);
    }
}
