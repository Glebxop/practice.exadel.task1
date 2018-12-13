package com.exadel.practice.task1;

import com.exadel.practice.task1.cswreaderwriter.MyCSVReader;
import com.exadel.practice.task1.cswreaderwriter.MyCSVWriter;
import com.exadel.practice.task1.model.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main2 {
    public static void main(String []arg) throws IOException {

        User once=new User(0,"Vasia","Vasia@gmail.com");
        User twice=new User(1,"Kolia","Kolia@gmail.com");
        User thrice=new User(2,"Petia","Petia@mail");
        Annotation annotation=new Annotation(2,twice,"LongWery","Text annotation");
        Attachment attachment=new Attachment(53,thrice,"Average",3.28);


        ArrayList<AbstractUserContent> annotationArrayList=new ArrayList<>();
       annotationArrayList.add(annotation);
       annotationArrayList.add(attachment);

       annotationArrayList.add(new Annotation(44,once,"title44","Text44"));
        annotationArrayList.add(new Annotation(47,thrice,"title47","Text47"));
        annotationArrayList.add(new Comment(49,thrice,"title47","Text47"));


        MyCSVWriter myCSVWriter=new MyCSVWriter();
        myCSVWriter.write("D:/UserContent.csv",annotationArrayList);
        MyCSVReader reader=new MyCSVReader();
        ArrayList<AbstractUserContent> arrayList= (ArrayList) reader.read("D:/Annotation.csv");
        System.out.println(arrayList.toString());
    }
}
