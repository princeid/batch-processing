package com.prince.jobmanagementsystem.service;

import com.prince.jobmanagementsystem.domain.Job;
import com.prince.jobmanagementsystem.domain.State;

import java.util.*;

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
                t.schedule(job, job.getScheduledTime());
            }
            else {
                job.run();
            }
        }
    }

}
