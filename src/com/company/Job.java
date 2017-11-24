package com.company;

import java.util.ArrayList;

public class Job {
    private ArrayList<String> jobData;
    private Company company;

    public Job(ArrayList<String> jobData,Company company)
    {
        this.jobData = jobData;
        this.company = company;
    }

    public ArrayList<String> getJobData() {
        return jobData;
    }

    public Company getCompany() {
        return company;
    }
}
