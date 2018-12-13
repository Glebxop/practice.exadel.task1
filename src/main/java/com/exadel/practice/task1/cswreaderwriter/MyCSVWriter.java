package com.exadel.practice.task1.cswreaderwriter;

import au.com.bytecode.opencsv.CSVWriter;
import com.exadel.practice.task1.model.AbstractUserContent;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyCSVWriter {


            private CSVWriter writer;

            public void write(String fileName, List<AbstractUserContent> list) throws IOException {

                writer=new CSVWriter(new FileWriter(fileName,true));
                for (int i = 0; i <list.size() ; i++) {
                  String split[]=getArrString(list.get(i).serializeToCsv());
                    writer.writeNext(split);
                }
                  writer.close();
            }

           private String[]getArrString(String csvString){
                String[] cash=csvString.split(",");
                return cash;
            }

}
