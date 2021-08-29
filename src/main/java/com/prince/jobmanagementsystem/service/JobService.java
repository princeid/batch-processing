package com.prince.jobmanagementsystem.service;

import com.prince.jobmanagementsystem.jobs.Job;
import com.prince.jobmanagementsystem.domain.State;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {
    Timer t = new Timer();

    List<Job> jobQueue = new ArrayList<>();

    public void addJob(Job job){
        /* Change job state to "QUEUED" after adding to the queue */
        job.setState(State.QUEUED);
        jobQueue.add(job);
    }

    public List<Job> getJobQueue() {
        return jobQueue;
    }

    public void setJobQueue(List<Job> jobQueue) {
        this.jobQueue = jobQueue;
    }

    public void runJobs(){
        Collections.sort(jobQueue);

        for (Job job : jobQueue) {
            if (job.getScheduledTime() != null){
                /* Run this job at the specified scheduled time */
                t.schedule(job, job.getScheduledTime());
            }
            else {
                job.run();
            }
        }
    }

}
