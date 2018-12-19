package com.exadel.practice.task1.cswreaderwriter;

import com.exadel.practice.task1.file.xml.UserCommentParsToXml;
import com.exadel.practice.task1.model.UserComment;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLWriter {


    public void write(String filePath,List<UserComment> userCommentlist) {
        UserCommentParsToXml userCommentParsToXml=new UserCommentParsToXml();
        try {

            //write file
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

