package com.example.JobPortal.repository;

import com.example.JobPortal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByJobType(String jobType);

    List<Job> findByName(String name);
}
