package com.example.JobPortal.controller;

import com.example.JobPortal.model.Job;
import com.example.JobPortal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class JobController {

    @Autowired
    JobService jobServiceObject;

    @PostMapping("/add-job")
    public String addJob(@RequestBody Job jobObject){
        jobServiceObject.save(jobObject);
        return "Job Added";
    }

    @GetMapping("/display-All-job")
    public List<Job> displayAllJob(){ return jobServiceObject.displayAllJob(); }

    @GetMapping("/display-By-JobName/{name}")
    public List<Job> displayByName(@PathVariable String name){ return jobServiceObject.displayByName(name); }

    @GetMapping("/display-By-JobType/{jobType}")
    public List<Job> displayByJobType(@PathVariable String jobType){ return jobServiceObject.displayByJobType(jobType); }

    @PutMapping("/update/{id}")
    public String updateJob(@PathVariable int jobId, @RequestBody Job jobObject){
        return jobServiceObject.UpdateJob(jobId, jobObject);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public String deleteById(@PathVariable int jobId){
        jobServiceObject.deleteByJobId(jobId);
        return "Job Deleted";
    }

    @DeleteMapping("delete-all-job")
    public String deleteAllJob(){
        jobServiceObject.deleteAllJob();
        return "All Jobs Deleted";
    }

}
