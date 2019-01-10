package com.exadel.practice.usercontent.mains;

import com.exadel.practice.usercontent.readerwriter.CsvReader;
import com.exadel.practice.usercontent.readerwriter.CsvWriter;
import com.exadel.practice.usercontent.file.csv.*;

import com.exadel.practice.usercontent.model.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] arg) throws IOException {
        List<Comment> commentsList = new ArrayList<>();
        List<Attachment> attachmentsList = new ArrayList<>();
        List<Annotation> annotationList = new ArrayList<>();



        User once = new User(0, "Vasia", "Vasia@gmail.com");
        User twice = new User(1, "Kolia", "Kolia@gmail.com");
        User thrice = new User(2, "Petia", "Petia@mail");
        Annotation annotation = new Annotation(2, twice, "LongWery", "Text annotation");
        Attachment attachment = new Attachment(53, thrice, "Average53", 37.28);
        Attachment attachment1 = new Attachment(54, once, "Average54", 3.2);
        Attachment attachment2 = new Attachment(55, twice, "Average55", 35.28);
        annotationList.add(annotation);
        attachmentsList.add(attachment);
        attachmentsList.add(attachment1);
        attachmentsList.add(attachment2);
        annotationList.add(new Annotation(44, once, "title44", "Text44"));
        annotationList.add(new Annotation(47, thrice, "title47", "Text47"));
        commentsList.add(new Comment(49, thrice, "title47", "Text47"));
        commentsList.add(new Comment(496, thrice, "title496", "Text496"));


        CsvWriter<Annotation> myCSVWriter = new CsvWriter<>(new AnnotationSerelizationCsv());
        myCSVWriter.write("D:/Annotation.csv", annotationList);
        CsvReader<Annotation> reader = new CsvReader<>(new AnnotationContentDeseralizerCsv());
        List<Annotation> arrayList = reader.read("D:/Annotation.csv");
        System.out.println(arrayList.toString());

        System.out.println();

        CsvWriter<Comment>myCSVWriterComment=new CsvWriter<>(new CommentContentSerializerCSV());
        myCSVWriterComment.write("D:/Comment.csv",commentsList);
        CsvReader<Comment>myCSVReaderComment=new CsvReader<>(new CommentContentDeserializerCSV());
        List<Comment> listComment=myCSVReaderComment.read("D:/Comment.csv");
        System.out.println(listComment.toString());

        System.out.println();

        CsvWriter<Attachment> myCSVWriterAttacment=new CsvWriter<>(new AttacmentContentSerealizationCsv());
        myCSVWriterAttacment.write("D:/Attacment.csv",attachmentsList);
        CsvReader<Attachment>myCSVReaderAttacment=new CsvReader<>(new AttacmentContentDeseralizerCsv());
        List<Attachment> attachmentList=myCSVReaderAttacment.read("D:/Attacment.csv");
        System.out.println(attachmentList.toString());





    }
}
