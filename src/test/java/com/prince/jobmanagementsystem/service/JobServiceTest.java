package com.prince.jobmanagementsystem.service;

import com.prince.jobmanagementsystem.JobManagementSystemApplication;
import com.prince.jobmanagementsystem.domain.Job;
import com.prince.jobmanagementsystem.domain.Priority;
import com.prince.jobmanagementsystem.jobs.CreateFileJob;
import com.prince.jobmanagementsystem.jobs.EmailSendingJob;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JobManagementSystemApplication.class)
class JobServiceTest {

    @Autowired
    JobService jobService;

    CreateFileJob createFileJob = new CreateFileJob(Priority.HIGH);
    EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.LOW);

    List<Job> jobQueue = new ArrayList<>();

    @Test
    void testAddJob() {
        jobService.addJob(createFileJob);
        jobService.addJob(emailSendingJob);
        jobQueue = jobService.getJobQueue();

        assertEquals(2, jobQueue.size());

    }

    @Test
    void testGetJobQueue() {
        jobQueue = jobService.getJobQueue();
        assertEquals(0, jobQueue.size());
    }

}
