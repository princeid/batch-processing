package com.prince.jobmanagementsystem.jobs;

import com.prince.jobmanagementsystem.domain.Priority;
import com.prince.jobmanagementsystem.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class CreateFileJob extends Job {
    Logger log = LoggerFactory.getLogger(CreateFileJob.class);

    public CreateFileJob(Priority priority) {
        super(priority);
    }

    @Override
    public void run() {
        setState(State.RUNNING);
        log.info("CREATE FILE JOB STATE IN RUN METHOD: {}", getState());
        log.info("File Creation Job is Running...");
        createFile();
    }

    private void createFile() {
        File file = new File("src/main/resources/file1.txt");
        try {
            if(file.createNewFile()){
                setState(State.SUCCESS);
            } else {
                setState(State.FAILED);
                log.info("File already exists.");
            }
        } catch (IOException e) {
            setState(State.FAILED);
            e.printStackTrace();
        }
    }

}
