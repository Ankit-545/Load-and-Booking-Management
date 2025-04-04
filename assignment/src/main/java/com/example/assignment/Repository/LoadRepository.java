package com.example.assignment.Repository;

import com.example.assignment.Entity.Load;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRepository extends JpaRepository<Load, UUID>,JpaSpecificationExecutor<Load> {

    
}
