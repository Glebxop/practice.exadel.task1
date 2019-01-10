package com.exadel.practice.usercontent.readerwriter;

import com.exadel.practice.usercontent.file.xml.UserCommentParsToXml;
import com.exadel.practice.usercontent.model.UserComment;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XmlWriter {


    public void write(String filePath,List<UserComment> userCommentlist) {
        UserCommentParsToXml userCommentParsToXml=new UserCommentParsToXml();
        try {


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(userCommentParsToXml.getXML(userCommentlist));
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

