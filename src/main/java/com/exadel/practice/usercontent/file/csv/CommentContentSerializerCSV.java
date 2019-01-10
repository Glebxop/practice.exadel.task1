package com.exadel.practice.usercontent.file.csv;

import com.exadel.practice.usercontent.model.Comment;


import java.util.ArrayList;
import java.util.List;

public class CommentContentSerializerCSV implements ContentSerializerCsv<Comment> {

    @Override
    public List<String[]> serializeToCsv(List<Comment> object) {
        ArrayList<String[]> afterSerialize=new ArrayList<>();
        String[] arrObject=new String[6];
        for (Comment comment : object) {
            arrObject[0] = String.valueOf(comment.getId());
            arrObject[1] = String.valueOf(comment.getUser().getId());
            arrObject[2] = comment.getUser().getName();
            arrObject[3] = comment.getUser().getEmail();
            arrObject[4] = comment.getTitle();
            arrObject[5] = comment.getText();
            afterSerialize.add(arrObject);
        }
        return afterSerialize;
    }


}
