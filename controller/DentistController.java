package com.example.odontologo.controller;

import com.example.odontologo.model.Dentist;
import com.example.odontologo.repository.impl.DentistDaoH2;
import com.example.odontologo.service.DentistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dentist")
public class DentistController {
    DentistService dentistService = new DentistService(new DentistDaoH2());

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentist(@PathVariable("id") long dentistID) {
        return ResponseEntity.status(200).body(dentistService.read(dentistID));
    }

    @GetMapping("/allDentist")
    public ResponseEntity<List<Dentist>> getAllDentist() {
        ResponseEntity<List<Dentist>> response = null;
        if(dentistService.showAll() == null) {
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return response;
        }

        return ResponseEntity.ok(dentistService.showAll());
    }

    @PostMapping("save")
    public ResponseEntity<Dentist> saveDentist(@RequestBody Dentist data) {
        ResponseEntity<Dentist> response = null;
        if(data == null) {
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return response;
        }

        return ResponseEntity.status(200).body(dentistService.save(data));
    }

    @PutMapping("/update")
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist) {
        ResponseEntity<Dentist> response = null;

        if(dentist.getId() != -1 && dentistService.read(dentist.getId()) != null) {
            response =  ResponseEntity.ok(dentistService.update(dentist));
            return response;

        }
        response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDentist(@PathVariable("id") long dentistId) {
        return ResponseEntity.ok(dentistService.delete(dentistId));

    }
}
