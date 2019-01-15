package com.exadel.practice.usercontent.dao.daocsv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;

import java.io.*;
import java.util.ArrayList;


import java.util.List;

public class CsvCommentDao implements Dao<Comment> {
    private String pathName;
    private CSVReader csvReader;

    public CsvCommentDao(String pathName) {
        this.pathName = pathName;

    }

    @Override
    public boolean add(Comment comment) {
        try {
            File file=new File(pathName);
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file, true));
            String[] arrComment = new String[6];
            arrComment[0] = String.valueOf(comment.getId());
            arrComment[1] = String.valueOf(comment.getUser().getId());
            arrComment[2] = comment.getUser().getName();
            arrComment[3] = comment.getUser().getEmail();
            arrComment[4] = comment.getText();
            arrComment[5] = comment.getTitle();
            csvWriter.writeNext(arrComment);
            csvWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    return false;}

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
    public boolean update(Comment comment) {
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if ((Integer.valueOf(nextLine[0])) != comment.getId()) {
                    list.add(nextLine);
                } else {
                    nextLine[1] = String.valueOf(comment.getUser().getId());
                    nextLine[2] = comment.getUser().getName();
                    nextLine[3] = comment.getUser().getEmail();
                    nextLine[4] = comment.getText();
                    nextLine[5] = comment.getTitle();
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
    public Comment get(int id) {
        Comment comment = null;
        String[] nextLine;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if (Integer.valueOf(nextLine[0]) == id) {
                    return new Comment(id, new User(Integer.valueOf(nextLine[1]), nextLine[2], nextLine[3]), nextLine[5], nextLine[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comment;
    }


    public String read() {
        String[] strings = null;
        try {
            strings = csvReader.readNext();
        } catch (IOException e) {
            strings[0] = "";
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

