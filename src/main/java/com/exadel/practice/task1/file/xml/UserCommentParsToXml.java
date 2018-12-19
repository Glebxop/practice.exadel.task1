package com.exadel.practice.task1.file.xml;


import com.exadel.practice.task1.model.Comment;
import com.exadel.practice.task1.model.UserComment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;


public class UserCommentParsToXml {
    Document doc;
    DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder icBuilder;


    public Document getXML(List<UserComment> userCommentlist) throws ParserConfigurationException {
        icBuilder = icFactory.newDocumentBuilder();
        doc = icBuilder.newDocument();
        Element mainRootElement = doc.createElement( "userComments");
        doc.appendChild(mainRootElement);


        // append child elements to root element
        for (
                UserComment userComment : userCommentlist) {
            mainRootElement.appendChild(getCompany(userComment));
        }

        return doc;

    }


    private Node getCompany(UserComment userComment) {
        Element company = doc.createElement("User");
        company.setAttribute("id", String.valueOf(userComment.getUser().getId()));
        company.setAttribute("Name", userComment.getUser().getName());
        company.setAttribute("Email", userComment.getUser().getEmail());

        for (Comment comment1 : userComment.getComment()) {

            Element comment = doc.createElement("Comment");
            comment.setAttribute("id", String.valueOf(comment1.getId()));
            comment.appendChild(getCompanyElements("title", comment1.getTitle()));
            comment.appendChild(getCompanyElements("text", comment1.getText()));
            company.appendChild(comment);
        }

        return company;
    }

    // utility method to create text node
    private Node getCompanyElements(String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}
