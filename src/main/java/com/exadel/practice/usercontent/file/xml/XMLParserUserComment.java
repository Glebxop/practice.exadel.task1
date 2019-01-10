package com.exadel.practice.usercontent.file.xml;

import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;
import com.exadel.practice.usercontent.model.UserComment;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.ArrayList;
import java.util.List;

public class XMLParserUserComment extends DefaultHandler {


    private String thisElement;
    private String[] userCommentArray;
    private List<UserComment> userCommentList;
    private List<Comment> commentList;
    private String[] commentArray;

    public XMLParserUserComment() {
        userCommentArray = new String[3];
        userCommentList = new ArrayList<>();
        commentArray = new String[3];
    }

    public List<UserComment> getUserCommentList() {
        return userCommentList;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


        thisElement = qName;
        if (thisElement.equalsIgnoreCase("user")) {
            getUserArray(attributes);
            commentList = new ArrayList<>();
        } else if (thisElement.equalsIgnoreCase("comment")) {
            commentArray[0] = attributes.getValue("id");
        }


    }

    private void getUserArray(Attributes attributes) {
        userCommentArray[0] = attributes.getValue(2);
        userCommentArray[1] = attributes.getValue(1);
        userCommentArray[2] = attributes.getValue(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (thisElement.equalsIgnoreCase("text")) {
            commentArray[1] = (String.valueOf(ch, start, length));
        } else if (thisElement.equalsIgnoreCase("title")) {
            commentArray[2] = String.valueOf(ch, start, length);

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("comment")) {
            commentList.add(new Comment(commentArray[1], Integer.valueOf(commentArray[0]), commentArray[2]));


        } else if (qName.equalsIgnoreCase("user")) {
            userCommentList.add(new UserComment(new User(Integer.valueOf(userCommentArray[1]), userCommentArray[0], userCommentArray[2]), commentList));

        }
    }

}
