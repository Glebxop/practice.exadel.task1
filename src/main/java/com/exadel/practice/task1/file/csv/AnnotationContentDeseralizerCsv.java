package com.exadel.practice.task1.file.csv;

import com.exadel.practice.task1.model.Annotation;
import com.exadel.practice.task1.model.User;

import java.util.ArrayList;
import java.util.List;

public class AnnotationContentDeseralizerCsv implements ContentDeserializerCSV<Annotation> {
    @Override
    public List<Annotation> deserializeFromCsv(List<String[]> listUserCntent) {

        List<Annotation> listAfterDeserialization=new ArrayList<>();

        for (String[] annotation:listUserCntent){
            listAfterDeserialization.add(new Annotation(Integer.valueOf(annotation[0]),
                    new User(Integer.valueOf(annotation[1]), annotation[2], annotation[3]), annotation[4], annotation[5]));
        }

        return listAfterDeserialization;
    }


}
