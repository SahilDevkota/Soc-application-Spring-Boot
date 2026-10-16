package com.example.SOCApplication.Repository;

import com.example.SOCApplication.Entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident,Integer>{
}
