package com.exadel.practice.task1.file.csv;

import com.exadel.practice.task1.model.Annotation;

import java.util.ArrayList;
import java.util.List;

public class AnnotationSerelizationCsv implements ContentSerializerCsv<Annotation> {

    @Override
    public List<String[]> serializeToCsv(List<Annotation> object) {
        String[]arrAnnotation=new String[6];
        List<String[]> listAfterSerialize=new ArrayList<>();
        for (Annotation annotation:object){
            arrAnnotation[0]=String.valueOf(annotation.getId());
            arrAnnotation[1] = String.valueOf(annotation.getUser().getId());
            arrAnnotation[2] = annotation.getUser().getName();
            arrAnnotation[3] = annotation.getUser().getEmail();
            arrAnnotation[4] = annotation.getTitle();
            arrAnnotation[5] = annotation.getText();
            listAfterSerialize.add(arrAnnotation);
        }
        return listAfterSerialize;
    }
}

