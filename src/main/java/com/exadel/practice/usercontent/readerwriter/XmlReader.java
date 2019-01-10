package com.exadel.practice.usercontent.readerwriter;

import com.exadel.practice.usercontent.file.xml.XMLParserUserComment;
import com.exadel.practice.usercontent.model.UserComment;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlReader {

    public List<UserComment> read(String pathName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        File file = new File(pathName);
        XMLParserUserComment saxp = new XMLParserUserComment();
        parser.parse(file, saxp);
       return saxp.getUserCommentList();
    }


}
