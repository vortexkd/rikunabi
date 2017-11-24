package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CsvReader {
    private String path;
    private ArrayList<String[]> data = new ArrayList<>();
    public CsvReader(String path) {
        this.path = path;
        File file = new File(this.path);
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader text = new BufferedReader(isr);
            String line;
            while((line=text.readLine())!=null) {
                this.data.add(line.split(","));
            }
            fis.close();
            text.close();
            isr.close();
        }
        catch(Exception e) {
            System.out.printf("Current path" + System.getProperty("user.dir"));
            System.out.println(e.getMessage());

        }
    }
    public ArrayList<String[]> getData(){
        return this.data;
    }
}
