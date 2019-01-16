package com.exadel.practice.usercontent.dao.daocsv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.exadel.practice.usercontent.Exception.CsvException;
import com.exadel.practice.usercontent.Exception.DaoExcepton;

import com.exadel.practice.usercontent.model.AbstractUserContent;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDaoCsv {

    protected String pathName;
    protected CSVReader csvReader;
    protected CSVWriter csvWriter;

    protected AbstractDaoCsv(String pathName) {
        this.pathName = pathName;

    }

    protected void initializeCsvWriter() throws IOException {
        csvWriter = new CSVWriter(new FileWriter(new File(pathName), true));
    }


    protected abstract String[] getArrayfromUserCont(AbstractUserContent abstractUserContent);


    protected boolean add(AbstractUserContent abstractUserContent) throws CsvException {
        try {
            initializeCsvWriter();
            csvWriter.writeNext(getArrayfromUserCont(abstractUserContent));
            csvWriter.close();
            return true;
        } catch (IOException e) {
            throw new CsvException(e.getMessage() + "add filed");
        }
    }

    protected List<String[]> listWithoutID(int id) throws IOException {
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        while ((nextLine = csvReader.readNext()) != null) {
            if ((Integer.valueOf(nextLine[0])) != id) {
                list.add(nextLine);
            }
        }
        return list;
    }

    public boolean dell(int id) throws CsvException {

        try {
            initalizeCsvReader();
            List<String[]> list = listWithoutID(id);
            csvReader.close();
            initializeCsvWriter();
            csvWriter.writeAll(list);
            csvWriter.close();
            return true;
        } catch (IOException e) {
            throw new CsvException(e.getMessage() + "dell filed");
        }

    }


    protected List<String[]> listAfterUpdate(AbstractUserContent abstractUserContent) throws IOException {
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        while ((nextLine = csvReader.readNext()) != null) {
            if ((Integer.valueOf(nextLine[0])) != abstractUserContent.getId()) {
                list.add(nextLine);
            } else {

                list.add(getArrayfromUserCont(abstractUserContent));
            }
        }
        return list;
    }

    public boolean update(AbstractUserContent abstractUserContent) throws CsvException {

        try {
            initalizeCsvReader();
            List<String[]> list = listAfterUpdate(abstractUserContent);
            csvReader.close();
            initializeCsvWriter();
            csvWriter.writeAll(list);
            csvWriter.close();
            return true;
        } catch (IOException e) {
            throw new CsvException(e.getMessage() + "up filed");
        }
    }


    public abstract AbstractUserContent get(int id) throws DaoExcepton;


    public String read() throws CsvException {
        String[] strings = null;
        try {
            strings = csvReader.readNext();
            return String.join(",", strings);
        } catch (IOException e) {
            throw new CsvException(e.getMessage());
        }

    }

    protected void initalizeCsvReader() throws FileNotFoundException {
        if (csvReader == null) {
            csvReader = new CSVReader(new FileReader(new File(pathName)));
        }
    }


    public boolean hasNext() throws CsvException {
        try {
            initalizeCsvReader();
            if (csvReader.readNext() != null) {
                return true;
            } else {
                csvReader.close();
                return false;
            }
        } catch (IOException e) {
            throw new CsvException("some trouble method hasNext");
        }

    }
}
