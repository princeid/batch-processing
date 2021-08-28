package com.prince.jobmanagementsystem.service;

import com.prince.jobmanagementsystem.domain.Job;
import com.prince.jobmanagementsystem.domain.State;

import java.util.LinkedList;
import java.util.Queue;

public class JobService {

    Queue<Job> jobQueue = new LinkedList<>();

    public void addJob(Job job){
        jobQueue.add(job);

        /* Change job state to "QUEUED" after adding to the queue */
        job.setState(State.QUEUED);
    }

    public Queue<Job> getJobQueue() {
        return jobQueue;
    }

    public void setJobQueue(Queue<Job> jobQueue) {
        this.jobQueue = jobQueue;
    }

}
