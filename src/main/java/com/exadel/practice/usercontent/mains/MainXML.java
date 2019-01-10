package com.exadel.practice.usercontent.mains;


import com.exadel.practice.usercontent.readerwriter.XmlReader;
import com.exadel.practice.usercontent.readerwriter.XmlWriter;
import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;
import com.exadel.practice.usercontent.model.UserComment;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainXML {
    public static void main(String[]arg) throws IOException, SAXException, ParserConfigurationException {
        XmlWriter writer=new XmlWriter();

        List<UserComment>userCommentList=new ArrayList<>();
        List<Comment> listComment=new ArrayList<>();
        listComment.add(new Comment("Text1", 1, "title1"));
        listComment.add(new Comment("Text2", 2, "title2"));
        User user1=new User(2, "Vasia", "Vasia@gmail.com");
        userCommentList.add(new UserComment(user1,listComment));

        List<Comment> listComment2=new ArrayList<>();
        listComment2.add(new Comment("Text3", 3, "title3"));
        listComment2.add(new Comment("Text4", 4, "title4"));
        User user2=new User(3, "Vova", "Vova@gmail.com");
        userCommentList.add(new UserComment(user2,listComment2));


        System.out.println("before xml:  "+userCommentList);
        writer.write("D:/MyXML.xml",userCommentList);

        XmlReader myXmlReader =new XmlReader();
        List<UserComment> userCommentList1= myXmlReader.read("D:/MyXML.xml");
        System.out.println("after Xml:   "+userCommentList1);
    }
}
