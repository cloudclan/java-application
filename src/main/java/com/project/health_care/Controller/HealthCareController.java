package com.project.health_care.Controller;

import com.project.health_care.Models.HeathCare;
import com.project.health_care.Service.HealthCareService;
import com.project.health_care.dto.Request.Response.HeathCareDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health")
public class HealthCareController {

    @Autowired
    private HealthCareService service;

    @GetMapping
    public List<HeathCareDTO> getAll(Authentication auth) {
        return service.getUserRecords(auth.getName());
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody HeathCare record, Authentication auth) {
        return service.createRecord(auth.getName(), record);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
