package knu.ahafonova.myroslava.db2022.lab3.controller;

import knu.ahafonova.myroslava.db2022.lab3.entity.Country;
import knu.ahafonova.myroslava.db2022.lab3.repository.CountryRepository;
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
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/country")
    public ResponseEntity<List<Country>> getAllCountries() {
        try {
            return ResponseEntity.ok(countryRepository.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable("id") int id) {
        return ResponseEntity.of(Optional.ofNullable(countryRepository.findById(id)));
    }

    @PostMapping("/country/")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        try {
            Country createdCountry = new Country(country.getId(), country.getName());
            countryRepository.save(createdCountry);
            return new ResponseEntity<>(createdCountry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<String> updateCountry(@PathVariable("id") int id, @RequestBody Country country) {
        Country _country = countryRepository.findById(id);
        if (_country != null) {
            _country.setId(id);
            _country.setName(country.getName());
            countryRepository.update(_country);
            return new ResponseEntity<>("Country was updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Country with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") int id) {
        try {
            int result = countryRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Country with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Country was deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete country.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/country")
    public ResponseEntity<String> deleteAllCountries() {
        try {
            int numRows = countryRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " countries successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete countries.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}