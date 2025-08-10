package com.project.health_care.Reposotory;

import com.project.health_care.Models.HeathCare;
import com.project.health_care.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthCareRepository extends JpaRepository<HeathCare, Long> {
    List<HeathCare> findByUser(Customer user);
}
