package com.prince.jobmanagementsystem.domain;

import java.util.Date;
import java.util.TimerTask;

public abstract class Job extends TimerTask implements Comparable<Job> {
    State state;
    Date scheduledTime;
    private Priority priority;

    public Job(Priority priority) {
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public abstract void run();

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Job job) {
        if(priority.getNumVal() < job.priority.getNumVal())
            return 1;
        else if(priority.getNumVal() > job.priority.getNumVal())
            return -1;
        return 0;
    }

}
