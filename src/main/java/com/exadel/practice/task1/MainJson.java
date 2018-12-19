package com.exadel.practice.task1;

import com.exadel.practice.task1.cswreaderwriter.ReaderJson;
import com.exadel.practice.task1.cswreaderwriter.WriterJson;
import com.exadel.practice.task1.model.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJson {
 public static void main(String[]arg) throws IOException {


     WriterJson myWriter=new WriterJson();
     List<Document> listDocument=new ArrayList<>();
     listDocument.add(new Document(55,"DocumentTitle","DocText"));
     listDocument.add(new Document(58,"DocumentTitle58","DocText58"));
     System.out.println(listDocument.toString());
     myWriter.write("D:/Json.json",listDocument);
     ReaderJson myReaderJson=new ReaderJson();
     List afterJson =myReaderJson.read("D:/Json.json");
     System.out.println(afterJson.toString());

 }

}
