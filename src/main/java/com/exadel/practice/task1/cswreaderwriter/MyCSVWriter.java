package com.exadel.practice.task1.cswreaderwriter;

import au.com.bytecode.opencsv.CSVWriter;
import com.exadel.practice.task1.file.csv.ContentSerializerCsv;



import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyCSVWriter<T> {

            private ContentSerializerCsv<T> contentSerializer;
            private CSVWriter writer;

    public MyCSVWriter(ContentSerializerCsv<T> contentSerializer) {
        this.contentSerializer = contentSerializer;
    }

    public void write(String fileName, List<T> list) throws IOException {

                writer=new CSVWriter(new FileWriter(fileName,true));
                List<String[]> fileContent = contentSerializer.serializeToCsv(list);
                writer.writeAll(fileContent);
                writer.close();
            }

    public void setContentSerializer(ContentSerializerCsv<T> contentSerializer) {
        this.contentSerializer = contentSerializer;
    }
}
