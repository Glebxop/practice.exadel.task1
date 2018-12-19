package com.exadel.practice.task1;

import com.exadel.practice.task1.cswreaderwriter.MyCSVReader;
import com.exadel.practice.task1.cswreaderwriter.MyCSVWriter;
import com.exadel.practice.task1.file.csv.*;

import com.exadel.practice.task1.model.*;


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


        MyCSVWriter<Annotation> myCSVWriter = new MyCSVWriter<>(new AnnotationSerelizationCsv());
        myCSVWriter.write("D:/Annotation.csv", annotationList);
        MyCSVReader<Annotation> reader = new MyCSVReader<>(new AnnotationContentDeseralizerCsv());
        List<Annotation> arrayList = reader.read("D:/Annotation.csv");
        System.out.println(arrayList.toString());

        System.out.println();

        MyCSVWriter<Comment>myCSVWriterComment=new MyCSVWriter<>(new CommentContentSerializerCSV());
        myCSVWriterComment.write("D:/Comment.csv",commentsList);
        MyCSVReader<Comment>myCSVReaderComment=new MyCSVReader<>(new CommentContentDeserializerCSV());
        List<Comment> listComment=myCSVReaderComment.read("D:/Comment.csv");
        System.out.println(listComment.toString());

        System.out.println();

        MyCSVWriter<Attachment> myCSVWriterAttacment=new MyCSVWriter<>(new AttacmentContentSerealizationCsv());
        myCSVWriterAttacment.write("D:/Attacment.csv",attachmentsList);
        MyCSVReader<Attachment>myCSVReaderAttacment=new MyCSVReader<>(new AttacmentContentDeseralizerCsv());
        List<Attachment> attachmentList=myCSVReaderAttacment.read("D:/Attacment.csv");
        System.out.println(attachmentList.toString());





    }
}
