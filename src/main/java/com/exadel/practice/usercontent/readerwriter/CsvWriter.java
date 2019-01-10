package com.exadel.practice.usercontent.readerwriter;

import au.com.bytecode.opencsv.CSVWriter;
import com.exadel.practice.usercontent.file.csv.ContentSerializerCsv;


import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter<T> {

    private ContentSerializerCsv<T> contentSerializer;
    private CSVWriter writer;

    public CsvWriter(ContentSerializerCsv<T> contentSerializer) {
        this.contentSerializer = contentSerializer;
    }

    public void write(String fileName, List<T> list) throws IOException {

        writer = new CSVWriter(new FileWriter(fileName, true));

        List<String[]> fileContent = contentSerializer.serializeToCsv(list);
        writer.writeAll(fileContent);
        writer.close();
    }

    public void setContentSerializer(ContentSerializerCsv<T> contentSerializer) {
        this.contentSerializer = contentSerializer;
    }
}
