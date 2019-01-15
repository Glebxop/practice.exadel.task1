package com.exadel.practice.usercontent.dao.daocsv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.Attachment;

import com.exadel.practice.usercontent.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvAttacmentDao implements Dao<Attachment> {
   private String pathName;
private CSVReader csvReader;
    public CsvAttacmentDao(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public boolean add(Attachment attachment) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(pathName), true));
            String[] arrComment = new String[6];
            arrComment[0] = String.valueOf(attachment.getId());
            arrComment[1] = String.valueOf(attachment.getUser().getId());
            arrComment[2] = attachment.getUser().getName();
            arrComment[3] = attachment.getUser().getEmail();
            arrComment[4] = String.valueOf(attachment.getFileSize());
            arrComment[5] = attachment.getTitle();
            csvWriter.writeNext(arrComment);
            csvWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());return false;
        }
    }

    @Override
    public boolean dell(int id) {
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
return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());return false;
        }
    }

    @Override
    public boolean update(Attachment attachment) {
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if ((Integer.valueOf(nextLine[0])) != attachment.getId()) {
                    list.add(nextLine);
                } else {
                    nextLine[1] = String.valueOf(attachment.getUser().getId());
                    nextLine[2] = attachment.getUser().getName();
                    nextLine[3] = attachment.getUser().getEmail();
                    nextLine[4] = String.valueOf(attachment.getFileSize());
                    nextLine[5] = attachment.getTitle();
                    list.add(nextLine);
                }
            }
            csvReader.close();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(pathName)));
            csvWriter.writeAll(list);
            csvWriter.close();
return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());return false;
        }
    }

    @Override
    public Attachment get(int id) {
        //Attachment attachment = null;
        String[] nextLine;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if (Integer.valueOf(nextLine[0]) == id) {
                    return new Attachment(id, new User(Integer.valueOf(nextLine[1]), nextLine[2], nextLine[3]), nextLine[5], Double.valueOf(nextLine[4]));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return null;
    }
    public String read()  {
        String[] strings = null;
        try {
            strings = csvReader.readNext();
        } catch (IOException e) {
            strings[0]="";
            System.out.println(e.getMessage());
        }
        return String.join(",",strings);
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
