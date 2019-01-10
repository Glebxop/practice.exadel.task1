package com.exadel.practice.usercontent.dao.daocsv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.Annotation;

import com.exadel.practice.usercontent.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvAnnotationDao implements Dao<Annotation> {
    private String pathName;
    private CSVReader csvReader;

    public CsvAnnotationDao(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public void add(Annotation annotation) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(pathName), true));
            String[] arrComment = new String[6];
            arrComment[0] = String.valueOf(annotation.getId());
            arrComment[1] = String.valueOf(annotation.getUser().getId());
            arrComment[2] = annotation.getUser().getName();
            arrComment[3] = annotation.getUser().getEmail();
            arrComment[4] = annotation.getText();
            arrComment[5] = annotation.getTitle();
            csvWriter.writeNext(arrComment);
            csvWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dell(int id) {
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if ((Integer.valueOf(nextLine[0])) != id) {
                    list.add(nextLine);
                }
            }
            csvReader.close();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(pathName)));
            csvWriter.writeAll(list);
            csvWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Annotation annotation) {
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if ((Integer.valueOf(nextLine[0])) != annotation.getId()) {
                    list.add(nextLine);
                } else {
                    nextLine[1] = String.valueOf(annotation.getUser().getId());
                    nextLine[2] = annotation.getUser().getName();
                    nextLine[3] = annotation.getUser().getEmail();
                    nextLine[4] = annotation.getText();
                    nextLine[5] = annotation.getTitle();
                    list.add(nextLine);
                }
            }
            csvReader.close();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(pathName)));
            csvWriter.writeAll(list);
            csvWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
            }

    @Override
    public Annotation get(int id) {
        Annotation annotation = null;
        String[] nextLine;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if (Integer.valueOf(nextLine[0]) == id) {
                    return annotation = new Annotation(id, new User(Integer.valueOf(nextLine[1]), nextLine[2], nextLine[3]), nextLine[5], nextLine[4]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return annotation;
    }

    public String read()  {
        String[] strings = null;
        try {
            strings = csvReader.readNext();
        } catch (IOException e) {
            strings[0]="";
            System.out.println(e.getMessage());
        }
        return String.join(",", strings);
    }

    public boolean hasNext() throws IOException {
        if (csvReader == null) {
            csvReader = new CSVReader(new FileReader(new File(pathName)));
        }

        if (csvReader.readNext() != null) {
            return true;
        } else {
            csvReader.close();
            return false;
        }

    }

}
