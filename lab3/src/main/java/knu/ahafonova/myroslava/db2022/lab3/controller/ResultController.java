package knu.ahafonova.myroslava.db2022.lab3.controller;

import knu.ahafonova.myroslava.db2022.lab3.entity.Result;
import knu.ahafonova.myroslava.db2022.lab3.repository.ResultRepository;
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
public class ResultController {
    @Autowired
    ResultRepository resultRepository;

    @GetMapping("/result")
    public ResponseEntity<List<Result>> getAllResults() {
        try {
            return ResponseEntity.ok(resultRepository.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable("id") int id) {
        return ResponseEntity.of(Optional.ofNullable(resultRepository.findById(id)));
    }

    @PostMapping("/result/")
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        try {
            Result createdResult = new Result(result.getCompetition_id(), result.getParticipant_id(), result.getResult());
            resultRepository.save(createdResult);
            return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/result/{id}")
    public ResponseEntity<String> updateResult(@PathVariable("id") int id, @RequestBody Result result) {
        Result _result = resultRepository.findById(id);
        if (_result != null) {
            _result.setCompetition_id(id);
            _result.setParticipant_id(_result.getParticipant_id());
            _result.setResult(result.getResult());
            resultRepository.update(_result);
            return new ResponseEntity<>("Result was updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Result with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/result/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable("id") int id) {
        try {
            int result = resultRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find result with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Result was deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete Result.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/result")
    public ResponseEntity<String> deleteAllResults() {
        try {
            int numRows = resultRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " results successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete results.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}