package com.prince.jobmanagementsystem;

import com.prince.jobmanagementsystem.domain.Priority;
import com.prince.jobmanagementsystem.jobs.CreateFileJob;
import com.prince.jobmanagementsystem.jobs.EmailSendingJob;
import com.prince.jobmanagementsystem.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class JobManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobManagementSystemApplication.class, args);

		Logger log = LoggerFactory.getLogger(JobManagementSystemApplication.class);

		CreateFileJob createFileJob = new CreateFileJob(Priority.MEDIUM);
		createFileJob.setScheduledTime(Date.from(Instant.ofEpochMilli(1630180560000L)));
		EmailSendingJob emailSendingJob = new EmailSendingJob(Priority.HIGH);

		JobService jobService = new JobService();
		jobService.addJob(createFileJob);
		jobService.addJob(emailSendingJob);

		log.info("CREATE FILE JOB STATE BEFORE RUN METHOD: {}", createFileJob.getState());
		log.info("EMAIL SENDING JOB STATE BEFORE RUN METHOD: {}", emailSendingJob.getState());

		jobService.runJobs();

		log.info("CREATE FILE JOB STATE AFTER RUN METHOD: {}", createFileJob.getState());
		log.info("EMAIL SENDING JOB STATE AFTER RUN METHOD: {}", emailSendingJob.getState());

	}

}
