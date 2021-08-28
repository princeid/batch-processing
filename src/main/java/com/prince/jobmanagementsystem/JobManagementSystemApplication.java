package com.prince.jobmanagementsystem;

import com.prince.jobmanagementsystem.domain.Job;
import com.prince.jobmanagementsystem.jobs.CreateFileJob;
import com.prince.jobmanagementsystem.jobs.EmailSendingJob;
import com.prince.jobmanagementsystem.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;
import java.util.Queue;

@SpringBootApplication
public class JobManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobManagementSystemApplication.class, args);

		Logger log = LoggerFactory.getLogger(JobManagementSystemApplication.class);

		CreateFileJob createFileJob = new CreateFileJob();
		EmailSendingJob emailSendingJob = new EmailSendingJob();

		JobService jobService = new JobService();
		jobService.addJob(createFileJob);
		jobService.addJob(emailSendingJob);

		log.info("CREATE FILE JOB STATE BEFORE RUN METHOD: {}", createFileJob.getState());
		log.info("EMAIL SENDING JOB STATE BEFORE RUN METHOD: {}", emailSendingJob.getState());

		Queue<Job> jobQueue = jobService.getJobQueue();

		for (Job job : jobQueue) {
			job.run();
		}

		log.info("CREATE FILE JOB STATE AFTER RUN METHOD: {}", createFileJob.getState());
		log.info("EMAIL SENDING JOB STATE AFTER RUN METHOD: {}", emailSendingJob.getState());

	}

}
