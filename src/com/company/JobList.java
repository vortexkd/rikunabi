package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class JobList {
    private ArrayList<String> fields;
    private ArrayList<Job> jobList = new ArrayList<>();
    private ArrayList<Company> companyList = new ArrayList<>();

    private final String companyFieldHeader = "企業名";
    private final String employeeTypeHeader = "雇用区分";
    private final String workLocationHeader = "勤務地都道府県";
    private final String lowSalaryHeader = "月給下限金額";
    private final String categoryHeader = "職種大分類";

    public JobList(ArrayList<String[]> d) {
        for(int i = 0; i<d.size();i++){
            int companyField;
            Company company = null;
            if(i==0){
                this.fields = new ArrayList<>(Arrays.asList(d.get(i)));
            }
            else{
                companyField = this.fields.indexOf(companyFieldHeader);
                String companyName = d.get(i)[companyField];
                for(Company c:companyList){
                    if(c.isCompany(companyName)){
                        company = c;
                        break;
                    }
                }
                if(company != null){
                    company.addJob();
                    this.jobList.add(new Job(new ArrayList<>(Arrays.asList(d.get(i))), company));
                }
                else {
                    company = new Company(companyName);
                    this.companyList.add(company);
                    this.jobList.add(new Job(new ArrayList<>(Arrays.asList(d.get(i))), company));

                }
            }
        }
    }

    public int getJobCount(){
        return this.jobList.size();
    }
    public int searchKoyouKubun(){
        String test = "契約社員";//can change this to search for selected input.
        int field = this.fields.indexOf(employeeTypeHeader);
        int count = 0;
        for (Job j:jobList) {
            if(getJobField(j,field).contains(test)){
                count++;
            }
        }
        return count;
    }

    public int[] getLocations(){
        int[] locationCounter = new int[47];
        for(Job j:jobList){
            int kenNumber = getKenNumber(j);
            if(kenNumber >= 0 && kenNumber < 47) {
                locationCounter[kenNumber]++;
            }
        }
        return locationCounter;
    }


    public double getAvgPayForCategory(String category){
        return getAvgPayByField(category,categoryHeader);
//        int categoryField = this.fields.indexOf(categoryHeader);
//        int lowSalaryField = this.fields.indexOf(lowSalaryHeader);
//        double total = 0;
//        int count = 0;
//        for(Job j:jobList){
//            if(getJobField(j,categoryField).equals(category)){
//                try {
//                    Double salary = Double.parseDouble(getJobField(j,lowSalaryField));
//                    total+=salary;
//                    count+=1;
//                }catch (NumberFormatException e) {
//                    //skip if error.
//                }
//            }
//        }
//        if(count == 0){
//            return 0;
//        }
//        return Math.round(total / count);

    }

    public double getAvgPayByCompany(String companyName){
        return getAvgPayByField(companyName,companyFieldHeader);
    }

    private double getAvgPayByField(String filter, String Header){
        int filterField = this.fields.indexOf(Header);
        int lowSalaryField = this.fields.indexOf(this.lowSalaryHeader);
        double total = 0;
        int count = 0;
        for(Job j:jobList){
            if(getJobField(j,filterField).equals(filter)){
                try {
                    Double salary = Double.parseDouble(getJobField(j,lowSalaryField));
                    total+=salary;
                    count+=1;
                }catch (NumberFormatException e) {
                    //skip if error.
                }
            }
        }
        if(count == 0){
            return 0;
        }
        return Math.round(total / count);
    }

    public ArrayList<Company> sortCompanies(){
        Collections.sort(this.companyList);
        return this.companyList;
    }

    private String getJobField(Job job, int field){
        return job.getJobData().get(field);
    }

    private int getKenNumber(Job job){
        int field = this.fields.indexOf(workLocationHeader);
        int answer =  Integer.parseInt(getJobField(job,field).split(":")[0]);
        return (answer-1);
    }

}
