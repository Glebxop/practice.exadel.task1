package com.exadel.practice.usercontent.mains;

import com.exadel.practice.usercontent.readerwriter.JsonReader;
import com.exadel.practice.usercontent.readerwriter.JsonWriter;
import com.exadel.practice.usercontent.model.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJson {
    public static void main(String[] arg) throws IOException {


        JsonWriter myWriter = new JsonWriter();
        List<Document> listDocument = new ArrayList<>();
        listDocument.add(new Document(55, "DocumentTitle", "DocText"));
        listDocument.add(new Document(58, "DocumentTitle58", "DocText58"));
        System.out.println(listDocument.toString());
        myWriter.write("D:/Json.json", listDocument);
        JsonReader myReaderJson = new JsonReader();
        List afterJson = myReaderJson.read("D:/Json.json");
        System.out.println(afterJson.toString());

    }

}
