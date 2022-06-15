package knu.ahafonova.myroslava.db2022.lab3.controller;

import knu.ahafonova.myroslava.db2022.lab3.entity.Competition;
import knu.ahafonova.myroslava.db2022.lab3.repository.CompetitionRepository;
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
public class CompetitionController {
    @Autowired
    CompetitionRepository competitionRepository;

    @GetMapping("/competition")
    public ResponseEntity<List<Competition>> getAllCompetitions() {
        try {
            return ResponseEntity.ok(competitionRepository.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/competition/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable("id") int id) {
        return ResponseEntity.of(Optional.ofNullable(competitionRepository.findById(id)));
    }

    @PostMapping("/competition/")
    public ResponseEntity<Competition> createCompetition(@RequestBody Competition competition) {
        try {
            Competition createdCompetition = new Competition(competition.getId(), competition.getPlace_id(), competition.getParticipant_id(), competition.getDay(), competition.getName());
            competitionRepository.save(createdCompetition);
            return new ResponseEntity<>(createdCompetition, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/competition/{id}")
    public ResponseEntity<String> updateCompetition(@PathVariable("id") int id, @RequestBody Competition competition) {
        Competition _competition = competitionRepository.findById(id);
        if (_competition != null) {
            _competition.setId(id);
            _competition.setPlace_id(competition.getPlace_id());
            _competition.setPlace_id(competition.getParticipant_id());
            _competition.setDay(competition.getDay());
            _competition.setName(competition.getName());
            competitionRepository.update(_competition);
            return new ResponseEntity<>("Competition was updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Competition with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/competition/{id}")
    public ResponseEntity<String> deleteCompetition(@PathVariable("id") int id) {
        try {
            int result = competitionRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Competition with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Competition was deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete Competition.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/competition")
    public ResponseEntity<String> deleteAllCompetitions() {
        try {
            int numRows = competitionRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " competitions successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete Competitions.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}