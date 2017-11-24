package com.company;

import java.util.ArrayList;

public class Company implements Comparable<Company>{
    private String name;
    //private ArrayList<Job> jobsOffered = new ArrayList<>(); <- expansion idea.
    private int jobCount;

    public Company(String name) {
        this.name = name;
        this.jobCount = 1;//company is only added when there's a job.
    }
    public void addJob(){
        this.jobCount++;
    }

    public String getName() {
        return name;
    }


    public int getJobCount() {
        return jobCount;
    }

    public boolean isCompany(String name){
        if(this.getName().equals(name)){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Company other) {
        if(this.jobCount>other.getJobCount()){
            return -1;
        }
        else if (this.jobCount<other.getJobCount()){
            return 1;
        }
        else{
            return 0;
        }
    }
}
