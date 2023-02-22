package com.example.JobPortal.service;

import com.example.JobPortal.model.Application;
import com.example.JobPortal.model.Job;
import com.example.JobPortal.repository.ApplicationRepository;
import com.example.JobPortal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepositoryObject;

    @Autowired
    JobRepository jobRepositoryObject;

    public String save(Application applicationObject)
    {
       List<Job> newJobObject= jobRepositoryObject.findAll();
      try{
          for (int i=1; i<= newJobObject.size(); i++)
          {
               String job = newJobObject.get(i).getName();

               if(job.equalsIgnoreCase(applicationObject.getNewJob().getName()))
                 {
                   if (applicationObject.getEx_army() == 1)
                   {
                    applicationRepositoryObject.save(applicationObject);
                    return "Application submitted successfully";
                  } else return "You are not eligible";
                 }
          }
        }catch(Exception ex){ return "catch: Job Not present -> You cannot apply"; }

       return "Job Not present -> You cannot apply";
    }


    public List<Application> displayAllApplication(){ return applicationRepositoryObject.findAll(); }

    public Optional<Application> displayById(int applicationId){ return applicationRepositoryObject.findById(applicationId); }


    public String deleteApplicationById(int applicationId){
        applicationRepositoryObject.deleteById(applicationId);
        return "Application Deleted";
    }

    public String deleteAllApplication(){
        applicationRepositoryObject.deleteAll();
        return "All Application Deleted";
    }

    public Optional<Application> displayApplicationById(int applicationId) {
        return applicationRepositoryObject.findById(applicationId);
    }

    public String UpdateApplication(int applicationId, Application applicationObject) {
        Optional<Application> newApplicationObject = applicationRepositoryObject.findById(applicationId);
        if(newApplicationObject!=null){
            applicationRepositoryObject.deleteById(applicationId);
            applicationRepositoryObject.save(applicationObject);
            return "Application Updated";
        }
        return "Application Not Updated";
    }

    public String deleteByApplicationId(int applicationId) {
        applicationRepositoryObject.deleteById(applicationId);
        return "Application Deleted";
    }
}
