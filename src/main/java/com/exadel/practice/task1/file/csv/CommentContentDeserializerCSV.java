package com.exadel.practice.task1.file.csv;

import com.exadel.practice.task1.model.Comment;
import com.exadel.practice.task1.model.User;

import java.util.ArrayList;
import java.util.List;

public class CommentContentDeserializerCSV implements ContentDeserializerCSV<Comment> {

    @Override
    public List<Comment> deserializeFromCsv(List<String[]> comments) {
        List<Comment> commentList = new ArrayList<>();

        for (String[] s : comments) {
            commentList.add(new Comment(Integer.valueOf(s[0]), new User(Integer.valueOf(s[1]), s[2], s[3]), s[4], s[5]));
        }

        return commentList;
    }


}
