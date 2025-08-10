package com.project.health_care.Service;

import com.project.health_care.Models.HeathCare;
import com.project.health_care.Models.Customer;
import com.project.health_care.Reposotory.HealthCareRepository;
import com.project.health_care.Reposotory.UserRepository;
import com.project.health_care.dto.Request.Response.HeathCareDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HealthCareService {
    @Autowired
    private HealthCareRepository healthRecordRepository;

    @Autowired
    private UserRepository userRepository;

    public List<HeathCareDTO> getUserRecords(String username) {
        Customer user = userRepository.findByUsername(username).orElseThrow();
        List<HeathCare> data = healthRecordRepository.findByUser(user);
        return data.stream().map(HealthCareService::toHeathCareDTO).collect(Collectors.toList());
    }

    public ResponseEntity<Object> createRecord(String username, HeathCare record) {
        Customer user = userRepository.findByUsername(username).orElseThrow();
        record.setUser(user);
        healthRecordRepository.save(record);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "Record created successfully"));
    }

    public Optional<HeathCare> getById(Long id) {
        return healthRecordRepository.findById(id);
    }

    public void deleteById(Long id) {
        healthRecordRepository.deleteById(id);
    }

    public static HeathCareDTO toHeathCareDTO(HeathCare entity) {
        HeathCareDTO dto = new HeathCareDTO();
        dto.setId(entity.getId());
        dto.setDiagnosis(entity.getBloodPressure());
        String notesString = entity.getNotes()
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
        dto.setNotes(notesString);
        dto.setUserName(entity.getUser().getUsername());

        return dto;
    }
}
