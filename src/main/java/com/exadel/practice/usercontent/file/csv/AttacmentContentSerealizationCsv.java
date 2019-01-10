package com.exadel.practice.usercontent.file.csv;

import com.exadel.practice.usercontent.model.Attachment;

import java.util.ArrayList;
import java.util.List;

public class AttacmentContentSerealizationCsv implements ContentSerializerCsv<Attachment> {
    @Override
    public List<String[]> serializeToCsv(List<Attachment> object) {
        List<String[]> listAfterSerilization=new ArrayList<>();
        String []arrLineAttacment=new String[6];

        for (Attachment attachment:object){
            arrLineAttacment[0] = String.valueOf(attachment.getId());
            arrLineAttacment[1] = String.valueOf(attachment.getUser().getId());
            arrLineAttacment[2] = attachment.getUser().getName();
            arrLineAttacment[3] = attachment.getUser().getEmail();
            arrLineAttacment[4] = attachment.getTitle();
            arrLineAttacment[5] = String.valueOf(attachment.getFileSize());
            listAfterSerilization.add(arrLineAttacment);

        }
        return listAfterSerilization;
    }

}
