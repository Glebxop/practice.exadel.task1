package com.exadel.practice.usercontent.file.csv;

import com.exadel.practice.usercontent.model.Attachment;
import com.exadel.practice.usercontent.model.User;

import java.util.ArrayList;
import java.util.List;

public class AttacmentContentDeseralizerCsv implements ContentDeserializerCSV<Attachment> {

    @Override
    public List<Attachment> deserializeFromCsv(List<String[]> listUserCntent) {

        List <Attachment> listAttacmentAfterDeser=new ArrayList<>();

        for (String[] s : listUserCntent) {
            listAttacmentAfterDeser.add(new Attachment(Integer.valueOf(s[0]),
                    new User(Integer.valueOf(s[1]), s[2], s[3]), s[4], Double.valueOf(s[5])));}

        return listAttacmentAfterDeser;
    }


}
