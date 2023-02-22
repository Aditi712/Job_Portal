package com.example.JobPortal.service;


import com.example.JobPortal.model.Job;
import com.example.JobPortal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepositoryObject;

    public void save(Job jobObject) { jobRepositoryObject.save(jobObject); }
    public List<Job> displayAllJob(){ return jobRepositoryObject.findAll(); }
    public List<Job> displayByName(String name) { return jobRepositoryObject.findByName(name); }

    public List<Job> displayByJobType(String jobType){ return jobRepositoryObject.findByJobType(jobType); }

    public void deleteByJobId(int id) { jobRepositoryObject.deleteById(id); }

    public String UpdateJob(int jobId, Job jobObject) {
        Optional<Job> newJobObject = jobRepositoryObject.findById(jobId);
        if(newJobObject!=null){
            jobRepositoryObject.deleteById(jobId);
            jobRepositoryObject.save(jobObject);
            return "Job Updated";
        }
        return "Job Not Updated";
    }

    public void deleteAllJob() { jobRepositoryObject.deleteAll(); }


}
