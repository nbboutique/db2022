package knu.ahafonova.myroslava.db2022.lab3.controller;

import knu.ahafonova.myroslava.db2022.lab3.entity.Participant;
import knu.ahafonova.myroslava.db2022.lab3.repository.ParticipantRepository;
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
public class ParticipantController {
    @Autowired
    ParticipantRepository participantRepository;

    @GetMapping("/participant")
    public ResponseEntity<List<Participant>> getAllParticipants() {
        try {
            return ResponseEntity.ok(participantRepository.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/participant/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable("id") int id) {
        return ResponseEntity.of(Optional.ofNullable(participantRepository.findById(id)));
    }

    @PostMapping("/participant")
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant participant) {
        try {
            Participant createdParticipant = new Participant(participant.getCountry_id(), participant.getId(), participant.getName(), participant.getSurname());
            participantRepository.save(createdParticipant);
            return new ResponseEntity<>(createdParticipant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/participant/{id}")
    public ResponseEntity<String> updateParticipant(@PathVariable("id") int id, @RequestBody Participant participant) {
        Participant _participant = participantRepository.findById(id);
        if (_participant != null) {
            _participant.setCountry_id(participant.getCountry_id());
            _participant.setId(id);
            _participant.setName(participant.getName());
            _participant.setSurname(participant.getSurname());
            participantRepository.update(_participant);
            return new ResponseEntity<>("Participant was updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find participant with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/participant/{id}")
    public ResponseEntity<String> deleteParticipant(@PathVariable("id") int id) {
        try {
            int result = participantRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find participant with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("participant was deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete participant.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/participant")
    public ResponseEntity<String> deleteAllParticipants() {
        try {
            int numRows = participantRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " participants.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete participant.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}