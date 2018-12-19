package com.exadel.practice.task1.cswreaderwriter;

import au.com.bytecode.opencsv.CSVReader;

import com.exadel.practice.task1.file.csv.ContentDeserializerCSV;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyCSVReader<T> {

    private CSVReader csvReader;

    private ContentDeserializerCSV<T> contentDeserializer;

    public MyCSVReader(ContentDeserializerCSV<T> contentDeserializer) {
        this.contentDeserializer = contentDeserializer;
    }

   public List<T> read(String fileCSVname ) throws IOException {
       String[] nextLine;
       List<T> listUserContent;
       ArrayList<String[]> arrayList=new ArrayList<>();
        csvReader=new CSVReader(new FileReader(fileCSVname));

        while ((nextLine=csvReader.readNext())!=null){
           arrayList.add(nextLine);}

       listUserContent= (List<T>) contentDeserializer.deserializeFromCsv(arrayList);
       return listUserContent;

   }



}
