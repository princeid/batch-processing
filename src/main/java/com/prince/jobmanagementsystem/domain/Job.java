package com.prince.jobmanagementsystem.domain;

import org.springframework.scheduling.annotation.Scheduled;

public abstract class Job {

    /* Set default job state to null */
    State state = null;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public abstract void run();

}
