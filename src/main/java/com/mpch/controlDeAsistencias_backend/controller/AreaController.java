package com.mpch.controlDeAsistencias_backend.controller;

import com.mpch.controlDeAsistencias_backend.model.Area;
import com.mpch.controlDeAsistencias_backend.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/area")
@CrossOrigin("*")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping("/add")
    public void addArea(@RequestBody Area area) {
        areaService.addArea(area);
    }

    @GetMapping("/id/{idArea}")
    public Area getAreaById(@PathVariable UUID idArea) {
        return areaService.getAreaById(idArea);
    }

    @GetMapping("/all")
    public Page<Area> getAllAreas(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return areaService.getAllAreas(pageable);
    }

    @GetMapping("/search/{name}")
    public List<Area> searchAreaByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return areaService.searchAreaByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/total")
    public Long getTotalAreas() {
        return areaService.getTotalAreas();
    }

    @PutMapping("/update/{idArea}")
    public Area updateArea(@PathVariable UUID idArea, @RequestBody Area area) {
        return areaService.updateArea(idArea, area);
    }

    @DeleteMapping("/delete/{idArea}")
    public void deleteArea(@PathVariable UUID idArea) {
        areaService.deleteArea(idArea);
    }

}
