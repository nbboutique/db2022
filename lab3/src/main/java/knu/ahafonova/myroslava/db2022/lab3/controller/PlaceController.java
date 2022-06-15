package knu.ahafonova.myroslava.db2022.lab3.controller;

import knu.ahafonova.myroslava.db2022.lab3.entity.Place;
import knu.ahafonova.myroslava.db2022.lab3.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class PlaceController {
    @Autowired
    PlaceRepository placeRepository;

    @GetMapping("/place")
    public ResponseEntity<List<Place>> getAllPlaces() {
        try {
            return ResponseEntity.ok(placeRepository.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @GetMapping("/place/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable("id") int id) {
        return ResponseEntity.of(Optional.ofNullable(placeRepository.findById(id)));
    }

    @PostMapping("/place/")
    public ResponseEntity<Place> createPlace(@RequestBody Place place) {
        try {
            Place createdPlace = new Place(place.getCountry_id(), place.getName(), place.getId());
            placeRepository.save(createdPlace);
            return new ResponseEntity<>(createdPlace, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/place/{id}")
    public ResponseEntity<String> updatePlace(@PathVariable("id") int id, @RequestBody Place place) {
        Place _place = placeRepository.findById(id);
        if (_place != null) {
            _place.setId(id);
            _place.setCountry_id(place.getCountry_id());
            _place.setName(place.getName());
            placeRepository.update(_place);
            return new ResponseEntity<>("Place was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find place with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/place/{id}")
    public ResponseEntity<String> deletePlace(@PathVariable("id") int id) {
        try {
            int place = placeRepository.deleteById(id);
            if (place == 0) {
                return new ResponseEntity<>("Cannot find place with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Place was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete place.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/place")
    public ResponseEntity<String> deleteAllPlace() {
        try {
            int numRows = placeRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " places.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete places.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}