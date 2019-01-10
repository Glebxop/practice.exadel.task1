package com.exadel.practice.usercontent.dao.daocsv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.exadel.practice.usercontent.dao.Dao;

import com.exadel.practice.usercontent.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUserDao implements Dao<User> {
    private String pathName;
    private CSVReader csvReader;

    public CsvUserDao(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public void add(User user) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(pathName), true));
            String[] arrComment = new String[3];
            arrComment[0] = String.valueOf(user.getId());
            arrComment[1] = user.getName();
            arrComment[2] = user.getEmail();
            csvWriter.writeNext(arrComment);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void update(User user) {

        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if ((Integer.valueOf(nextLine[0])) != user.getId()) {
                    list.add(nextLine);
                } else {
                    nextLine[1] = user.getName();
                    nextLine[2] = user.getEmail();
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
    public User get(int id) {
        User user = null;
        String[] nextLine;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if (Integer.valueOf(nextLine[0]) == id) {
                    return user = new User(id, nextLine[1], nextLine[2]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public String read() {
        String[] strings = null;
        try {
            strings = csvReader.readNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.join(",", strings);
    }

    public boolean hasNext() throws Exception {
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
