package com.prince.jobmanagementsystem.domain;

import com.prince.jobmanagementsystem.JobManagementSystemApplication;
import com.prince.jobmanagementsystem.jobs.CreateFileJob;
import com.prince.jobmanagementsystem.jobs.EmailSendingJob;
import com.prince.jobmanagementsystem.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JobManagementSystemApplication.class)
class JobTest {

    @Autowired
    JobService jobService;

    @Test
    void getNullState() {
        EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.MEDIUM);
        State state = emailSendingJob.getState();
        assertNull(state);
    }

    @Test
    void getNonNullState() {
        EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.MEDIUM);
        jobService.addJob(emailSendingJob);
        State state = emailSendingJob.getState();
        assertNotNull(state);
        assertEquals(State.QUEUED, state);
    }

    @Test
    void testJobRunMethodNotQUEUED() {
        CreateFileJob createFileJob = new CreateFileJob(Priority.MEDIUM);
        jobService.addJob(createFileJob);
        createFileJob.run();
        State state = createFileJob.getState();
        assertNotNull(state);
        assertNotEquals(State.QUEUED, state);
    }

    @Test
    void testJobRunMethodNotNULL() {
        CreateFileJob createFileJob = new CreateFileJob(Priority.MEDIUM);
        jobService.addJob(createFileJob);
        createFileJob.run();
        State state = createFileJob.getState();
        assertNotNull(state);
        assertNotEquals(null, state);
    }

    @Test
    void setState() {
        CreateFileJob createFileJob = new CreateFileJob(Priority.HIGH);
        createFileJob.setState(State.FAILED);
        assertEquals(State.FAILED, createFileJob.getState());
    }
}