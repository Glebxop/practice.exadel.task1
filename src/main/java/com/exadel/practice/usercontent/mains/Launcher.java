package com.exadel.practice.usercontent.mains;

import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launcher {

    public void start() {

        List<Comment> commentList = new ArrayList<>();
        User user1 = new User(2, "VasiaUpdate", "Vasia@gmail.com");
        commentList.add(new Comment(23, new User(1, "Vasia", "Vasia@gmail.com"), "Title Comment23", "TextComment23"));
        commentList.add(new Comment(24, new User(1, "Vasia", "Vasia@gmail.com"), "Title Comment24", "TextComment24"));
        Comment one = new Comment(23, new User(1, "Vasia888", "Vasia@gmail.com8"), "Title Comment286", "TextComment277");
        Comment two = new Comment(24, new User(1, "Kolia", "Kolia@gmail.com"), "Title Comment24", "TextComment24");
        Attachment twoAtt = new Attachment(3, new User(45, "Kolia", "Kolia@gmail.com"), "TitleUpdate", 5.877);
        Attachment twoAttt = new Attachment(4, new User(46, "Ko", "Ko@gmail.com"), "Update", 5.7);
        Annotation three = new Annotation(264, new User(446, "Vania", "Vania@gmail.com"), "Title Comment24Vania", "TextComment24Mania");
        List<Attachment> listAtt = new ArrayList<>();
        listAtt.add(twoAtt);
        listAtt.add(twoAttt);
        Document document = new Document(2, "TittleDoc", "TextDoc", listAtt);



    }
}
