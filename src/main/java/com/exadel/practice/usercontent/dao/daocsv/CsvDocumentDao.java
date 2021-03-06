package com.exadel.practice.usercontent.dao.daocsv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.exadel.practice.usercontent.Exception.CsvException;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Attachment;
import com.exadel.practice.usercontent.model.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvDocumentDao extends AbstractDaoCsv implements Dao<Document> {


    public CsvDocumentDao(String pathName) {
        super(pathName);
    }

    @Override
    public boolean add(Document document) throws CsvException {
        try {
            initializeCsvWriter();
            String[] arrComment = new String[(document.getUserContentList().size() * 3) + 3];
            int count = 0;
            arrComment[count] = String.valueOf(document.getId());
            arrComment[count += 1] = document.getTitle();
            arrComment[count += 1] = document.getText();


            for (Attachment attachment : document.getUserContentList()) {
                arrComment[count += 1] = String.valueOf(attachment.getId());
                arrComment[count += 1] = attachment.getTitle();
                arrComment[count += 1] = String.valueOf(attachment.getFileSize());
            }

            csvWriter.writeNext(arrComment);
            csvWriter.close();
            return true;
        } catch (IOException e) {
            throw new CsvException("document didn't add. trouble with csw writer");
        }
    }



    @Override
    public boolean update(Document document) throws CsvException {
        String[] nextLine;
        List<String[]> list = new ArrayList<>();
        try {
            initalizeCsvReader();
            while ((nextLine = csvReader.readNext()) != null) {
                if ((Integer.valueOf(nextLine[0])) != document.getId()) {
                    list.add(nextLine);
                } else {
                    nextLine[1] = document.getTitle();
                    nextLine[2] = document.getText();
                    list.add(nextLine);
                }
            }
            csvReader.close();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(pathName)));
            csvWriter.writeAll(list);
            csvWriter.close();
return true;
        } catch (IOException e) {
            throw new CsvException("document didn't update. trouble with csw writer or reader");
        }


    }

    @Override
    protected String[] getArrayfromUserCont(AbstractUserContent abstractUserContent) {
        return new String[0];
    }

    @Override
    public Document get(int id) throws CsvException {
        Document document = null;
        String[] nextLine;
        List<Attachment> attachmentList = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(pathName)));
            while ((nextLine = csvReader.readNext()) != null) {
                if (Integer.valueOf(nextLine[0]) == id) {

                    for (int i = 3; i < nextLine.length; i += 3) {
                        attachmentList.add(new Attachment(nextLine[i + 1], Integer.valueOf(nextLine[i]), Double.valueOf(nextLine[i + 2])));
                    }
                    return new Document(id, nextLine[1], nextLine[2], attachmentList);
                }
            }
        } catch (IOException e) {
            throw new CsvException("document didn't get. trouble with csw writer or reader");
        }

        return document;
    }

}
