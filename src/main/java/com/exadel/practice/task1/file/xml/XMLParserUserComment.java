package com.exadel.practice.task1.file.xml;

import com.exadel.practice.task1.model.Comment;
import com.exadel.practice.task1.model.User;
import com.exadel.practice.task1.model.UserComment;
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
    public void startDocument() throws SAXException {
        super.startDocument();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        thisElement = qName;
        //System.out.println(thisElement);
        if (thisElement.equalsIgnoreCase("User")) {
            getUserArray(attributes);
            commentList = new ArrayList<>();
        } else if (thisElement.equalsIgnoreCase("Comment")) {
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
        super.characters(ch, start, length);
        if (thisElement.equalsIgnoreCase("text")) {
            commentArray[1] = (String.valueOf(ch, start, length));
        } else if (thisElement.equalsIgnoreCase("title")) {
            commentArray[2] = String.valueOf(ch, start, length);

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equalsIgnoreCase("comment")) {
            commentList.add(new Comment(commentArray[1], Integer.valueOf(commentArray[0]), commentArray[2]));


        }
        if (qName.equalsIgnoreCase("user")) {
            userCommentList.add(new UserComment(new User(Integer.valueOf(userCommentArray[0]), userCommentArray[1], userCommentArray[2]), commentList));

        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        //  System.out.println("end doc");
    }
}
