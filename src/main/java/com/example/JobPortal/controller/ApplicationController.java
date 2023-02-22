package com.example.JobPortal.controller;

import com.example.JobPortal.model.Application;
import com.example.JobPortal.model.Job;
import com.example.JobPortal.model.User;
import com.example.JobPortal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationServiceObject;

    @PostMapping("/add-Application")
    public String addApplication(@RequestBody Application applicationObject){
       return applicationServiceObject.save(applicationObject);
    }

    @GetMapping("/display-All-Application")
    public List<Application> displayAllApplication(){ return applicationServiceObject.displayAllApplication(); }

    @RequestMapping(value="/display-By-application-id/{id}",method=RequestMethod.GET)
    public Optional<Application> displayByApplicationId(@PathVariable int id){
        return applicationServiceObject.displayApplicationById(id); }

    @PutMapping("/update-application/{id}")
    public String updateApplication(@PathVariable int id, @RequestBody Application applicationObject){
        return applicationServiceObject.UpdateApplication(id, applicationObject);
    }

    @DeleteMapping("/delete-by-application-id/{id}")
    public String deleteByApplicationId(@PathVariable int applicationId){
        applicationServiceObject.deleteByApplicationId(applicationId);
        return "Application Deleted";
    }

    @DeleteMapping("delete-all-Application")
    public String deleteAllApplication(){
        applicationServiceObject.deleteAllApplication();
        return "All User Deleted";
    }

}
