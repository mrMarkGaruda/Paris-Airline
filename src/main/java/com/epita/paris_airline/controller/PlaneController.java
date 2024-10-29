package com.epita.paris_airline.controller;

import com.epita.paris_airline.model.Plane;
import com.epita.paris_airline.service.PlaneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public List<Plane> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @PostMapping
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        Plane savedPlane = planeService.savePlane(plane);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlane);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Plane> getPlaneById(@PathVariable Long id) {
        Plane plane = planeService.getPlaneById(id);
        return plane != null ? ResponseEntity.ok(plane) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plane> updatePlane(@PathVariable Long id, @RequestBody Plane planeDetails) {
        Plane updatedPlane = planeService.updatePlane(id, planeDetails);
        return updatedPlane != null ? ResponseEntity.ok(updatedPlane) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long id) {
        boolean isDeleted = planeService.deletePlane(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
