package com.exadel.practice.task1.cswreaderwriter;

import au.com.bytecode.opencsv.CSVReader;
import com.exadel.practice.task1.model.Factory;
import com.exadel.practice.task1.model.AbstractUserContent;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyCSVReader {

    private CSVReader csvReader;

    public ArrayList<AbstractUserContent> read(String fileCSVname) throws IOException {
        csvReader = new CSVReader(new FileReader(fileCSVname));
        String[] nextLine;
        ArrayList<AbstractUserContent> arrayList = new ArrayList<>();
        //String line;
        Factory factory = new Factory();

        while ((nextLine = csvReader.readNext()) != null) {
            arrayList.add(factory.abstractUserContentNew(nextLine));

            // line = String.join(",", nextLine);


// arrayList.add(new Annotation().deserializeFromCsv(line));
        }
        return arrayList;
    }
}
