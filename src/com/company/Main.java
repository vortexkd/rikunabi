package com.company;


import jdk.nashorn.internal.scripts.JO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        CsvReader csvReader = new CsvReader(System.getProperty("user.dir")+"/files/tc2_RNN-TC_rikunavi_next.csv");
        JobList jobList = new JobList(csvReader.getData());

        System.out.println("全記載件数 : " + jobList.getJobCount());
        System.out.println("雇用区分が契約社員の件数　: "+jobList.searchKoyouKubun());

        //print47KenJobs(jobList);

        getAvgSalary(jobList,"アミューズメント"); //ここに変化

        //getTopTen(jobList);

        compareMostandLeast(jobList);






//        for(String[] s: csvReader.getData()){
//            int i = 0;
//            String toPrint = "";
//            for(String field:s){
//                if(i!=2) {
//                    toPrint += field + "\t";
//                }
//                i++;
//            }
//            System.out.println(toPrint);
//        }

    }

    private static void print47KenJobs(JobList jobList){
        ArrayList<String> kenList = new ArrayList<>(Arrays.asList("北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県", "茨城県", "栃木県", "群馬県",
                "埼玉県", "千葉県", "東京都", "神奈川県", "新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県", "静岡県",
                "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県", "奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県",
                "山口県", "徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県",
                "沖縄県"));


        int[] counterList = jobList.getLocations();
        for(int i = 0;i<counterList.length;i++){
            System.out.println(kenList.get(i)+" : "+counterList[i]+"件");
        }
    }

    private static void getAvgSalary(JobList jobList, String category){
        double avgPay = jobList.getAvgPayForCategory(category);
        DecimalFormat df = new DecimalFormat("#,###0");
        System.out.println(df.format(avgPay));
    }
    private static void getTopTen(JobList jobList){
        ArrayList<Company> companyList = jobList.sortCompanies();
        int i = 0;
        int j = 1;
        int previousJob = 0;
        while(j<11){
            System.out.println(j+"位 "+companyList.get(i).getName()+" : "+companyList.get(i).getJobCount()+"件");
            i++;
            if(previousJob!=companyList.get(i).getJobCount()){
                j++;
                previousJob = companyList.get(i).getJobCount();
            }
        }
    }

    public static void compareMostandLeast(JobList jobList){
        ArrayList<Company> companyList = jobList.sortCompanies();
        DecimalFormat df = new DecimalFormat("#,###0");
        double total = 0;
        double count = 0;
        for(Company c:companyList.subList(0,25)){
            double avg = jobList.getAvgPayByCompany(c.getName());
            System.out.println(avg);
            if(avg!=0) {
                total += avg;
                count+=1;
            }
        }
        System.out.println("件数が多い会社の平均　: "+df.format(total / count));
        System.out.println("*******************");
        total = count = 0;
        for (Company c: companyList.subList(Math.max(companyList.size()-25,0),companyList.size())){
            double avg = jobList.getAvgPayByCompany(c.getName());
            System.out.println(avg);
            if(avg!=0) {
                total += avg;
                count+=1;
            }
        }

        System.out.println("件数が多い会社の平均　: "+df.format(total / count));
    }

}
