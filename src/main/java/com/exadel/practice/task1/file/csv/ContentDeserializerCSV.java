package com.exadel.practice.task1.file.csv;

import com.exadel.practice.task1.model.AbstractUserContent;

import java.util.List;

public interface ContentDeserializerCSV<T> {
    List<T> deserializeFromCsv(List<String[]> listUserCntent);

}
