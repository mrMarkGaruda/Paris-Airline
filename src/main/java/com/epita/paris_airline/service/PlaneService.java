package com.epita.paris_airline.service;

import com.epita.paris_airline.model.Plane;
import com.epita.paris_airline.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {
    @Autowired
    private PlaneRepository planeRepository;

    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    public Plane savePlane(Plane plane) {
        return planeRepository.save(plane);
    }

    public Plane getPlaneById(Long id) {
        return planeRepository.findById(id).orElse(null);
    }

    public Plane updatePlane(Long id, Plane planeDetails) {
        Optional<Plane> optionalPlane = planeRepository.findById(id);
        if (optionalPlane.isPresent()) {
            Plane plane = optionalPlane.get();
            plane.setBrand(planeDetails.getBrand());
            plane.setModel(planeDetails.getModel());
            plane.setManufacturingYear(planeDetails.getManufacturingYear());
            return planeRepository.save(plane);
        }
        return null;
    }

    public boolean deletePlane(Long id) {
        if (planeRepository.existsById(id)) {
            planeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
